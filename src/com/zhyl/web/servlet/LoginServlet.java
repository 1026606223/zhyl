package com.zhyl.web.servlet;

import com.zhyl.bean.UserInfo;
import com.zhyl.service.Service;
import com.zhyl.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet",urlPatterns = "/servlet/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("ID");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        Service svc = new ServiceImpl();
        UserInfo uifo = null;
        try {
            uifo = svc.loginVerify(id, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(uifo!=null){
            HttpSession session = request.getSession();
            session .setAttribute("uifo",uifo);
            session.setMaxInactiveInterval(7*24*3600);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(7*24*3600);
            response.addCookie(cookie);

            //request.getRequestDispatcher("/UserInfo.jsp").forward(request,response);
            request.getRequestDispatcher("/Position.html").forward(request,response);
        }
        else{
            String href=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/Login.jsp";
            out.print("<body><script type=\"text/javascript\">alert('用户名或密码错误！');location.href=\""+href+"\";</script><body>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
