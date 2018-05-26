<%--
  Created by IntelliJ IDEA.
  User: 育  Danger
  Date: 2018/5/25
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>【官方】登录系统</title>
</head>
<body>
<h1 align="cnter">欢迎进入登录系统</h1>
    <form action="/login" method="post">
        <div>
            <%=request.getAttribute("loginInfo")==null?"\n":request.getAttribute("loginInfo")%>
        </div>
        用户名：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        <input type="submit" value="登录">
    </form>

</body>
</html>
