package site.binghai.biz.filter;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import site.binghai.biz.enums.UserRule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author huaishuo
 * @date 2019/1/6 下午3:39
 **/
public abstract class BaseUserFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            if (session.getAttribute(getRule().name()) != null) {
                return true;
            }
        }
        response.sendRedirect(getRedirectUrl(session));
        return false;
    }

    /**
     * 登录地址
     */
    protected abstract String getRedirectUrl(HttpSession session);

    /**
     * 身份类型
     */
    public abstract UserRule getRule();

}
