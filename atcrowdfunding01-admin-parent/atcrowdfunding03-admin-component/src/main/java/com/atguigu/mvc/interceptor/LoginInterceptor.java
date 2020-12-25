package com.atguigu.mvc.interceptor;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.通过request对象获取session对象
        HttpSession httpSession = request.getSession();
        // 2.尝试从Session中获取Admin对象
        Admin admin = (Admin) httpSession.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        // 3.判断Admin对象是否为空
        if(null == admin){
            // 4.如果为空抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }
        // 5.如果Admin不为空就返回True
        return true;
    }
}
