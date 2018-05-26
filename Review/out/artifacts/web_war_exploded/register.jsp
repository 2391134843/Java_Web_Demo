<%--
  Created by IntelliJ IDEA.
  User: 育  Danger
  Date: 2018/5/25
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>【官方】注册系统</title>
</head>
<body>
    <form action="/register" style="margin-top: 5px;" method="post">
        用户名：<input type="text" name="username" placeholder="请输入用户名"><br>
        密码：<input type="password" name="password" placeholder="请输入密码"><br>
        确认密码：<input type="password"  placeholder="请再次确认密码"><br>
        姓名：<input type="name" name="name" placeholder="请输入姓名"><br>
        <label>性别:</label>

            <label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio1" value="male">男
                </label>
            <label class="radio-inline"> <input type="radio" name="sex" id="inlineRadio2" value="female">女
                </label>
        <br>
        电话：<input type="text" name="telephone" placeholder="请输入电话号码"><br>
        email: <input type="text" name="email" placeholder="Email"><br>
        <input type="submit" value="注册">
    </form>
</body>
</html>
