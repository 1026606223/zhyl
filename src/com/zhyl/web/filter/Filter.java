package com.zhyl.web.filter;

import com.zhyl.service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter",urlPatterns = {"/Login.jsp","/UserInfo.jsp"})
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        //通过判断有无session来确定用户是否已登录，来进行页面跳转
        if(session.getAttribute("uifo")!=null){
            //req.getRequestDispatcher("/UserInfo.jsp").forward(req,resp);
            if(request.getRequestURI().contains("UserInfo.jsp")) chain.doFilter(req,resp);
            else{
                response.sendRedirect(request.getContextPath()+"/UserInfo.jsp");
            }
        }
        else{
            String uri = request.getRequestURI();
            if(request.getRequestURI().contains("Login.jsp")) chain.doFilter(req, resp);
            else response.sendRedirect(request.getContextPath()+"/Login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
