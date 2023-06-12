<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách khách hàng</title>
    <jsp:include page="/layout/head.jsp"></jsp:include>
</head>
<body>
<style>
    button{
        border-radius: 10px;
    }
    table{
        width: 80%;
        margin-top: 10px;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<form method="post" id="frmHiden" action="/customers?action=delete">
    <input type="hidden" id="txtIdEdit" name="idEdit"  />
</form>
<div class="container">
    <div class="row d-flex justify-content-center">
        <c:if test="${requestScope.message != null}">
            <script>
                window.onload = function (){
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: ${message},
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            </script>
        </c:if>
    </div>
    <h2 class="text-center">Danh sách khách hàng</h2>
    <form action="">

    </form>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Type</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.customers}" var="c">
            <tr>
                <td>${c.getName()}</td>
                <td>${c.getEmail()}</td>
                <td>${c.getAddress()}</td>
                <td>${c.getTypeName()}</td>
                <td>
                    <a href="/customers?action=edit&id=${c.getId()}"><i class="fa-solid fa-user-pen"></i></a>
                    <a href="javascript: void(0)" onclick="handleDelete('${c.getId()}','${c.getName()}')"><i class="fa-solid fa-trash"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class=" d-flex justify-content-center align-items-center ">
        <a href="/customers?action=create&id=${id}">
            <button type="submit" class="btn btn-success mr-1">Add Customer</button>
        </a>
        <a href="/customers?action=restore&id=${id}">
            <button type="submit" class="btn btn-info ml-1">Deleted Customer</button>
        </a>
    </div>
    <script>


        function handleDelete(id,name) {
            document.getElementById("txtIdEdit").value = id;
                Swal.fire({
                    title: 'Do you want to delete' + name + "?",
                    showDenyButton: true,
                    showCancelButton: false,
                    confirmButtonText: 'Yes',
                    denyButtonText: 'No',
                    position: 'top-end',
                    customClass: {
                        actions: 'my-actions',
                        cancelButton: 'order-1 right-gap',
                        confirmButton: 'order-2',
                        denyButton: 'order-3',
                    }
                }).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById("frmHiden").submit()

                    } else {
                            Swal.fire({
                                position: 'top-end',
                                icon: 'error',
                                title: 'Xoá thất bại',
                                showConfirmButton: false,
                                timer: 1500
                            })
                    }
                })

        }


    </script>
</div>
<jsp:include page="layout/footer_js.jsp"></jsp:include>
</body>
</html>