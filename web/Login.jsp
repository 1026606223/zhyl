<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 2018/8/28
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" session="false" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <style type="text/css">
        body {
            height: 100%;
            width: 100%;
            background-image: url("Image/BackGRound.jpeg");
            background-size: 100%;
        }
    </style>
    <link href="CSS/LoginCss.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<h1>智慧养老</h1>
<form action="${pageContext.request.contextPath}/servlet/loginServlet" method="post">
    <div>
        <input id="username" name="ID" type="text" required="required" placeholder="账号"/>
        <input id="password" type="password" name="password" required="required" placeholder="密码"/>
    </div>
    <div>
        <input id="LoginButton" type="submit" value="登录"/>
    </div>
</form>
<div>
    <input id="RetrieveButton" type="submit" value="忘记密码"/>
    <input id="RegisterButton" type="submit" value="新用户注册"/>
</div>
</body>
</html>