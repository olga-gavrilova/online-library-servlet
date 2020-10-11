package com.gmail.olyagavrilova.onlinelibrary.servlet;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Role;
import com.gmail.olyagavrilova.onlinelibrary.model.UserDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserDto userDto = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();

        if(path.contains("/admin/users")) {
            if (userDto != null && Role.ADMIN.equals(userDto.getRole())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletResponse.getWriter().append("AccessDenied");
            }
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
