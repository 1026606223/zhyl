package com.zhyl.web.servlet;

import com.zhyl.bean.UserInfo;
import com.zhyl.service.Service;
import com.zhyl.service.impl.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "RegisterServlet",urlPatterns = "/servlet/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        UserInfo uifo = new UserInfo();
        uifo.setID(request.getParameter("ID"));
        uifo.setGender(request.getParameter("Gender"));
        uifo.setPassword(request.getParameter("Password"));
        uifo.setRealName(request.getParameter("RealName"));
        uifo.setPhone(request.getParameter("Phone"));
        String year =request.getParameter("year");
        String month =request.getParameter("month");
        String day =request.getParameter("day");
        String datestring = year+"-"+month+"-"+day;
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(datestring);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        uifo.setBirthday(date);

        Service svc = new ServiceImpl();
        try {
            svc.register(uifo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将用户信息存储到session域中完成登录
        HttpSession session = request.getSession();
        session.setAttribute("uifo",uifo);
        session.setMaxInactiveInterval(7*24*3600);
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(7*24*3600);
        response.addCookie(cookie);

        request.getRequestDispatcher("/UserInfo.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
