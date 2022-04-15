<%--
  Created java.by IntelliJ IDEA.
  User: NK
  Date: 06.04.2022
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${sessionScope.user == null}">
    <p>Hello guest</p>
    <a href="/registration">Registration</a>
    <a href="/authorization">Authorization</a>
</c:if>

<c:if test="${sessionScope.user != null}">
    <p>Hello ${sessionScope.user.name}</p>
    <a href="/logout">Logout</a>
</c:if>
</body>
</html>

