package site.binghai.biz.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.binghai.biz.entity.CustomerOrder;
import site.binghai.biz.entity.User;
import site.binghai.biz.service.CustomerOrderService;
import site.binghai.lib.controller.BaseController;

import java.util.List;
import java.util.Map;

/**
 * @author huaishuo
 * @date 2019/1/6 下午4:22
 **/
@RestController
@RequestMapping("/normal/")
public class NormalUserController extends BaseController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("myInfo")
    public Object myInfo() {
        User user = getSessionPersistent(User.class);
        user.setPassword(null);
        return success(user, null);
    }

    /**
     * 增
     */
    @PostMapping("addOrder")
    public Object addOrder(@RequestBody Map map) {
        User user = getSessionPersistent(User.class);

        CustomerOrder order = customerOrderService.newInstance(map);
        if (hasEmptyString(order.getDoorType())) {
            return fail("必须指定类型");
        }

        order.setId(null);
        order.setFactoryId(user.getFactoryId());

        order = customerOrderService.save(order);

        return success(order, null);
    }

    /**
     * 删
     */
    public Object deleteOrder(@RequestParam Long id) {
        User user = getSessionPersistent(User.class);
        CustomerOrder order = customerOrderService.findById(id);
        if (order == null) {
            return fail("要删除的记录不存在，可能已经被删除");
        }

        if (!order.getFactoryId().equals(user.getFactoryId())) {
            logger.warn("{} try to delete other factory order :{}", user, order);
            return fail("不得非法删除其他工厂的订单！");
        }
        //记录日志
        logger.warn("{} delete order {}", user, order);
        //删除操作
        customerOrderService.delete(id);

        return success();
    }

    /**
     * 改
     */
    @PostMapping("updateOrdre")
    public Object updateOrder(@RequestBody Map map) {
        User user = getSessionPersistent(User.class);

        CustomerOrder neworder = customerOrderService.newInstance(map);
        if (hasEmptyString(neworder.getId())) {
            return fail("必须指定更新id");
        }

        CustomerOrder old = customerOrderService.findById(neworder.getId());
        if (old == null) {
            return fail("要更新的记录不存在，可能已经被删除");
        }

        //记录日志
        logger.warn("{} update order from {} to {}", user, old, neworder);
        //更新操作
        customerOrderService.update(neworder);

        return success();
    }

    /**
     * 查
     */
    @GetMapping("listOrder")
    public Object listOrder(@RequestParam Integer page) {
        if (page <= 0) {
            page = 1;
        }

        User user = getSessionPersistent(User.class);

        List<CustomerOrder> orders = customerOrderService.findByFactoryIdAndPage(user.getFactoryId(), page);
        Long total = customerOrderService.countByFactoryId(user.getFactoryId());

        JSONObject ret = newJSONObject();
        ret.put("page", page);
        ret.put("total", total);
        ret.put("pageSize", 100);
        ret.put("list", orders);

        return success(ret, null);
    }

    /**
     * 搜索
     */
    @PostMapping("query")
    public Object query(@RequestBody Map map) {
        CustomerOrder query = customerOrderService.newInstance(map);
        query.setId(null);

        List<CustomerOrder> queryResult = customerOrderService.query(query);

        if (isEmptyList(queryResult)) {
            return fail("没有找到任何记录，请尝试减少查询条件");
        }

        return success(queryResult, null);
    }
}
