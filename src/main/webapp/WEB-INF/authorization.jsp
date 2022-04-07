<%--
  Created by IntelliJ IDEA.
  User: NK
  Date: 06.04.2022
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form action="/authorization" method="post">
    <input type="text" name="login" placeholder="login">
    <input type="password" name="password" placeholder="password">
    <button>Submit</button>
</form>
<p>${requestScope.message}</p>
</body>
</html>