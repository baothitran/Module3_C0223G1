<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
      integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<head>
  <title>Danh sách khách hàng đã xoá</title>
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
    button{
      border-radius: 10px;
    }
    .add{
      display: flex;
      justify-content: center;
    }
  </style>
</head>
<body>
<form method="post" id="frmHiden" action="/customer?action=restore">
  <input type="hidden" id="txtIdEdit" name="idEdit"  />
</form>
<h2 class="head">Danh sách khách hàng đã xoá</h2>

<table class="table">

  <thead>
  <tr>
    <th scope="col">ID</th>
    <th scope="col">Tên</th>
    <th scope="col">Email</th>
    <th scope="col">Địa chỉ</th>
    <th scope="col">Loại</th>
    <th scope="col">Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.id}</td>
      <td>${customer.name}</td>
      <td>${customer.email}</td>
      <td>${customer.address}</td>
      <td>${customer.typeName}</td>
      <td>
        <a onclick="handleRestore('${customer.getId()}','${customer.getName()}')"><i class="fa-solid fa-arrow-rotate-left"></i></a>
      </td>
    </tr>
  </c:forEach>

  </tbody>
</table>
<script>
  function handleRestore(id,name) {
    document.getElementById("txtIdEdit").value = id;
    let cf = confirm("Bạn có chắc chắn muốn khôi phục " + name);
    if (cf) {
      document.getElementById("frmHiden").submit();
    }
  }
</script>
</body>
</html>