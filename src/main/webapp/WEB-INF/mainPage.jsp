<%--
  Created by IntelliJ IDEA.
  User: NK
  Date: 07.04.2022
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>add some items</title>
</head>
<body>

<c:if test="${sessionScope.user != null}">
    <form action="/addproduct" method="post">
        <input type="text" name="productname" placeholder="product">
        <button>Add</button>
    </form>

</c:if>

<c:if test="${sessionScope.user != null}">
    <a href="/logout">Logout</a>
</c:if>

</body>
</html>
