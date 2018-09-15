package com.zhyl.web.servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletTst", urlPatterns = "/servlet/tst")
public class ServletTst extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("aaa","laoge");
        session.setMaxInactiveInterval(1000000);
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/demo1");
        response.addCookie(cookie);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
