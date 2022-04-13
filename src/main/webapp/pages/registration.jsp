<%--
  Created by IntelliJ IDEA.
  User: NK
  Date: 06.04.2022
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="/registration " method="post">
    <input type="text" name = "username" placeholder="Имя">
    <input type="text" name = "login" placeholder="Логин">
    <input type="password" name = "password" placeholder="Пароль">
    <button>Submit</button>
</form>
<p>${requestScope.message}</p>
</body>
</html>