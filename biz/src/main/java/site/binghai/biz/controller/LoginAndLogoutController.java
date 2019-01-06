package site.binghai.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.binghai.biz.entity.User;
import site.binghai.biz.service.UserService;
import site.binghai.lib.enums.ErrorCode;
import site.binghai.lib.controller.BaseController;

import java.util.Map;

/**
 * @author huaishuo
 * @date 2019/1/3 下午5:12
 **/
@RestController
@RequestMapping("/")
public class LoginAndLogoutController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Object login(@RequestBody Map map) {
        String mail = getString(map, "mail");
        String pass = getString(map, "pass");

        if (hasEmptyString(mail, pass)) {
            return fail(ErrorCode.ILLEGAL_ARGUMENT);
        }

        User user = userService.findByMailAndPass(mail, pass);
        if (user == null) {
            return fail("用户名或密码错误");
        }

        user.setPassword(null);
        // 放入session
        persistent(user);

        return success(user, null);
    }
}
