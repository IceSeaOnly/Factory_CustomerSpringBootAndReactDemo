package site.binghai.biz.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.biz.enums.DoorTypeEnum;
import site.binghai.lib.controller.BaseController;

/**
 * @author huaishuo
 * @date 2019/1/6 下午5:53
 **/
@RestController
@RequestMapping("/api/v1/")
public class ApiController extends BaseController {
    @GetMapping("doorTypes")
    public Object doorTypes() {
        JSONArray arr = newJSONArray();

        for (DoorTypeEnum value : DoorTypeEnum.values()) {
            JSONObject item = newJSONObject();
            item.put("label", value.getDoorName());
            item.put("value", value.name());

            arr.add(item);
        }

        return success(arr, null);
    }
}
