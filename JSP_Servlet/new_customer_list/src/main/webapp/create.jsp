<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 6/11/2023
  Time: 1:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/layout/head.jsp"></jsp:include>
    <style>
        .container {
            height: 100%;
            margin-top: 50px;
            border: 1px solid lightgray;
            width: 600px !important;
        }
        h2 {
            text-align: left;
            margin-bottom: 30px;
        }
        label{
            font-weight: bold;
            float: right;
        }
        .footer{
            display: flex;
            justify-content: flex-end;
        }
        .btn{
            margin: 8px;
        }
    </style>
</head>
<body>
<form action="/customers?action=create" method="post">
    <div class="container">
        <div class="add-form">
            <h2>Thêm khách hàng</h2>
            <c:if test="${requestScope.errors != null}">
                <div class="alert alert-danger">
                    <ul>
                        <c:forEach items="${errors}" var="e">
                            <li>${e}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <div class="form-group row">
                <label class="col-3">Name </label>
                <div class="col-9">
                    <input type="text" name="name" value="${customer.getName()}" class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-3">Email </label>
                <div class="col-9">
                    <input type="text" name="email" value="${customer.getEmail()}" class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-3">Address</label>
                <div class="col-9">
                    <input type="text" name="address" value="${customer.getAddress()}" class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-3">Type</label>
                <div class="col-9">
                    <select name="typeId" class="form-control">
                        <c:forEach var="c" items="${customerTypes}">
                            <option value="${c.getId()}">${c.getType()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>

        <div class="footer">
            <button type="submit" class="btn btn-primary">Save</button>
            <button type="button" class="btn btn-danger">Cancel</button>
        </div>

    </div>
</form>
<jsp:include page="layout/footer_js.jsp"></jsp:include>
</body>
</html>
