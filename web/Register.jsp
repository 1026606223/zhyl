<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 2018/8/25
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" contentType="text/html; utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <style type="text/css">
        body {
            height: 100%;
            width: 100%;
            background-image: url("Image/BackGRound.jpeg");
            background-size: 100% auto;
        }
    </style>
    <link href="CSS/RegisterCss.css" type="text/css" rel="stylesheet"/>
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
        //用户名合法返回true，否则返回false
        function ckName(inputdom){
            var id = inputdom.value;
            if(id==""){
                inputdom.value="用户名不能为空！"
            }
            else{
                //创建XMLHttpRequest对象
                var xhr = getXMLHttpRequest();
                //处理结果
                xhr.onreadystatechange = function(){
                    if(xhr.readyState==4){//请求一切正常
                        if(xhr.status==200){//服务器响应一切正常
                            // 得到结果并处理结果
                            if (xhr.responseText == "true") {
                                inputdom.value="用户名已存在！";
                            }
                        }
                    }
                }
                //创建连接
                xhr.open("get","${pageContext.request.contextPath}/servlet/checkNameServlet?id="+id);
                //发送请求
                xhr.send(null);
            }
        }
    </script>
</head>
<body>
<h1>智慧养老</h1>
<form action="${pageContext.request.contextPath}/servlet/registerServlet" method="post">
    <div id="TextBox">
        <div id="p1">账号：</div>
        <input id="ID" name="ID" type="text" required="required" onblur="ckName(this)" onclick="(function(dom){
          if(dom.value == '用户名已存在！') dom.value='';})(this)"/>
        <div id="p2">密码：</div>
        <input id="Password" name="Password" type="password" required="required" minlength="6"/>
        <div id="p3">连接设备号：</div>
        <input id="EquipmentNumber" name="EquipmentNumber" type="text" required="required"/>
        <div id="p4">手机号：</div>
        <input id="PhoneNumber" name="Phone" type="text" minlength="11" maxlength="11" required="required"/>
        <div id="p5">姓名：</div>
        <input id="Name" name="RealName" type="text" required="required"/>
        <div id="p6">性别：</div>
        <select id="Sex" name="Gender">
            <option value="男" selected="selected">男</option>
            <option value="女">女</option>
        </select>
        <div id="p7">出生日期：</div>
        <div id="Birthday">
            <select id="year" name="year" onchange="load_day()">
                <option value="1930">1930</option>
                <option value="1931">1931</option>
                <option value="1932">1932</option>
                <option value="1933">1933</option>
                <option value="1934">1934</option>
                <option value="1935">1935</option>
                <option value="1936">1936</option>
                <option value="1937">1937</option>
                <option value="1938">1938</option>
                <option value="1939">1939</option>
                <option value="1940">1940</option>
                <option value="1941">1941</option>
                <option value="1942">1942</option>
                <option value="1943">1943</option>
                <option value="1944">1944</option>
                <option value="1945">1945</option>
                <option value="1946">1946</option>
                <option value="1947">1947</option>
                <option value="1948">1948</option>
                <option value="1949">1949</option>
                <option value="1950">1950</option>
                <option value="1951">1951</option>
                <option value="1952">1952</option>
                <option value="1953">1953</option>
                <option value="1954">1954</option>
                <option value="1955">1955</option>
                <option value="1956">1956</option>
                <option value="1957">1957</option>
                <option value="1958">1958</option>
                <option value="1959">1959</option>
                <option value="1960">1960</option>
                <option value="1961">1961</option>
                <option value="1962">1962</option>
                <option value="1963">1963</option>
                <option value="1964">1964</option>
                <option value="1965">1965</option>
                <option value="1966">1966</option>
                <option value="1967">1967</option>
                <option value="1968">1968</option>
                <option value="1969">1969</option>
                <option value="1970">1970</option>
            </select>
            <b id="bar1">-</b>
            <select id="month" name="month" onchange="load_day()">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <b id="bar2">-</b>
            <select id="day" name="day">
            </select>
        </div>
        <div id="p8">验证码：</div>
        <input id="IdCode" name="IdCode" type="text" required="required"/>
    </div>
    <div>
        <input id="RegisterButton" type="submit" value="注册"/>
    </div>
</form>
</body>
<script type="text/javascript">
    function day31() {
        return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
    }

    function day30() {
        return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, 26, 27, 28, 29, 30]
    }

    function day29() {
        return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, 26, 27, 28, 29]
    }

    function day28() {
        return [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
            12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, 26, 27, 28]
    }

    var days31 = day31();
    var days30 = day30();
    var days29 = day29();
    var days28 = day28();

    function load_day() {
        var y = document.getElementById("year");
        var m = document.getElementById("month");
        var d = document.getElementById("day");

        var y1 = parseInt(y.value);
        var m1 = parseInt(m.value);
        if (m1 < 8 && m1 % 2 == 1 || m1 >= 8 && m1 % 2 == 0) {

            for (var i = 0; i < days31.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = days31[i];
                opt.value = days31[i];
                d.appendChild(opt);
            }
        } else if ((y1 % 400 == 0 || y1 % 100 != 0 && y1 % 4 == 0) && m1 == 2) {
            for (var i = 0; i < days29.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = days29[i];
                opt.value = days29[i];
                d.appendChild(opt);
            }
        } else if (m1 == 2) {
            for (var i = 0; i < days28.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = days28[i];
                opt.value = days28[i];
                d.appendChild(opt);
            }
        } else {
            for (var i = 0; i < days30.length; i++) {
                var opt = document.createElement("option");
                opt.innerHTML = days30[i];
                opt.value = days30[i];
                d.appendChild(opt);
            }
        }
    }
</script>
</html>