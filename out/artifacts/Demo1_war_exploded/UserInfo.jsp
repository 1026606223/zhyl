<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 2018/8/27
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <style type="text/css">
        body {
            height: 100%;
            width: 100%;
        }
    </style>
    <link href="CSS/PersonalInformationCss.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div id="NavigationBar">
    <a href="Position.html">
    <img id="back" src="Image/back.png"/>
    </a>
    <h1>个人中心</h1>
    <img id="setting" src="Image/setting.png"/>
</div>
<div id="Middle">
    <img id="person" src="Image/person.png"/>
    <div id="id">账号</div>
    <div style=" position:absolute;
    top: 20%;
    right: 0%;
    color: rgba(0,0,0,0.87);
    font-size: 70px;
    ">${uifo.ID}</div>
    <img id="key" src="Image/key.png"/>
    <div id="uid">设备号</div>
    <img id="quit" src="Image/quit.png"/>
    <div id="qt" onclick="logout()">退出登录</div>
</div>
<div id="TaskBar">
    <a href="Position.html">
    <div id="Task1">老人位置</div>\
    </a>
    <a href="Health.html">
    <div id="Task2">健康状况</div>
    </a>
    <div id="Task3">个人中心</div>
    <div id="Selectbar"></div>
</div>
</body>
<script type="text/javascript">
    //用于获取ajax xmlhttprequest的函数
    function getXMLHttpRequest(){
        var xmlhttp;
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }
        else{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        return xmlhttp;
    }

    function logout(){
        var xhr = getXMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState==4){//请求一切正常
                if(xhr.status==200){//服务器响应一切正常
                    // 得到结果并处理结果
                    if (xhr.responseText == "ok") {
                        alert("退出登录成功！");
                        location.href = "Login.jsp";
                    }
                }
            }
        }
        xhr.open("get","${pageContext.request.contextPath}/servlet/logoutServlet");
        xhr.send(null);
    }
</script>
</html>