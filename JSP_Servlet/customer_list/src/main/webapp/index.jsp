<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<head>
    <title>Danh sách khách hàng</title>
    <style>
        .table{
            width: 80%;
            margin-top: 10px;
            margin-left: auto;
            margin-right: auto;
        }
        .head{
            display: flex;
            justify-content: center;
            margin-top: 100px;
        }
    </style>
</head>
<body>
<h2 class="head">Danh sách khách hàng</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Tên</th>
        <th scope="col">Ngày sinh</th>
        <th scope="col">Địa  chỉ</th>

    </tr>
    </thead>
    <tbody>
<c:forEach var="customer" items="${customers}">
    <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
        <td>${customer.dob}</td>
        <td>${customer.address}</td>
    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>