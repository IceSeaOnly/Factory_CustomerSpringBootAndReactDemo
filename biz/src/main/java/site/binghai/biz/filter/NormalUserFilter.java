package site.binghai.biz.filter;

import site.binghai.biz.enums.UserRule;

import javax.servlet.http.HttpSession;

/**
 * 普通用户拦截器
 * @author huaishuo
 * @date 2019/1/6 下午3:41
 **/
public class NormalUserFilter extends BaseUserFilter {
    @Override
    protected String getRedirectUrl(HttpSession session) {
        return "/normalLogin#user/login";
    }

    @Override
    public UserRule getRule() {
        return UserRule.NORMAL;
    }
}
