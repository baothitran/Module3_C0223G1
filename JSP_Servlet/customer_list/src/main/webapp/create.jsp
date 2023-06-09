<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 6/7/2023
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<c:if test="${message != null}">
    <h3>Thêm thành công</h3>
</c:if>
<form method="post" action="/customer?action=create">
    <h3>Add</h3>
    <table>
        <tr>
            <td>Name: </td>
            <td><input type="text" name="name" id="name" value="${customer.getName()}"></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email" id="email" value="${customer.getEmail()}"></td>
        </tr>
        <tr>
            <td>Address: </td>
            <td><input type="text" name="address" id="address" value="${customer.getAddress()}"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Add customer"></td>
        </tr>
    </table>
</form>
</body>
</html>

