package site.binghai.lib.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import site.binghai.lib.def.UnifiedOrderMethods;
import site.binghai.lib.entity.PayBizEntity;
import site.binghai.lib.entity.UnifiedOrder;
import site.binghai.lib.entity.WxUser;
import site.binghai.lib.enums.OrderStatusEnum;
import site.binghai.lib.enums.PayBizEnum;
import site.binghai.lib.service.PayBizServiceFactory;
import site.binghai.lib.service.SysConfigService;
import site.binghai.lib.service.UnifiedOrderService;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * 具有支付需求的controller继承此controller
 * */
public abstract class AbstractPayBizController<T extends PayBizEntity> extends BaseController {
    private T instance;

    @Autowired
    protected PayBizServiceFactory payBizServiceFactory;

    @Autowired
    private UnifiedOrderService unifiedOrderService;

    @Autowired
    public SysConfigService sysConfigService;

    public PayBizServiceFactory getPayBizServiceFactory() {
        return payBizServiceFactory;
    }

    public UnifiedOrderService getUnifiedOrderService() {
        return unifiedOrderService;
    }

    protected UnifiedOrderMethods<T> getService() {
        try {
            return payBizServiceFactory.get(getBizType());
        } catch (Exception e) {
            logger.error("cannot getService for Class {}", getTypeArguement().getCanonicalName());
        }
        return null;
    }

    /**
     * 获取T的实际类型
     */
    public Class<T> getTypeArguement() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    private PayBizEnum getBizType() throws Exception {
        if (instance == null) {
            instance = getTypeArguement().newInstance();
        }
        return instance.getBizType();
    }

    public JSONObject create(Map map, int fee) throws Exception {
        if(sysConfigService.isSystemClosed()){
            return fail(sysConfigService.getCloseReason());
        }

        T uorder = getService().newInstance(map);

        WxUser user = getSessionPersistent(WxUser.class);

        uorder.setStatus(OrderStatusEnum.CREATED.getCode());
        uorder.setPaid(Boolean.FALSE);
        uorder.setUserId(user.getId());

        UnifiedOrder unifiedOrder = unifiedOrderService.newOrder(
                getBizType(),
                user,
                getBizType().getName(),
                fee);

        uorder.setUnifiedId(unifiedOrder.getId());

        uorder = getService().save(uorder);
        return success(uorder, "/user/unified/detail?unifiedId=" + unifiedOrder.getId());
    }
}
