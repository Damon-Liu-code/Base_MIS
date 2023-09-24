package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        // 检查用户是否已登录
        boolean isLoggedIn = (session != null && session.getAttribute("currentUser") != null);

        // 登录页面的URI
        String loginURI = request.getContextPath() + "/login";
        String loginPageURI = request.getContextPath() + "/pages/login.html";
        String reqUrl = request.getRequestURI();

        // 如果用户未登录，将请求重定向到登录页面
        if (!isLoggedIn && !reqUrl.equals(loginURI) && !reqUrl.equals(loginPageURI)
                && !reqUrl.contains("/js") && !reqUrl.contains("/css") && !reqUrl.contains("/images")
                && !reqUrl.contains(".ico") && !reqUrl.contains("/jquery")) {
            response.sendRedirect(loginPageURI);
            return;
        }

        // 用户已登录，继续处理请求
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 销毁方法
    }
}
