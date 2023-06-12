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
<div class="container">
    <form method="post" id="frmHiden" action="/customers?action=restore">
        <input type="hidden" id="txtIdEdit" name="idEdit"  />
    </form>
    <h2 class="text-center">Danh sách khách hàng đã xoá</h2>
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
                    <a href="javascript: void(0)" onclick="handleRestore('${c.getId()}','${c.getName()}')"><i class="fa-solid fa-trash-can-arrow-up fa-lg"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script>
        function handleRestore(id,name) {
            document.getElementById("txtIdEdit").value = id;

            Swal.fire({
                title: 'Do you want to restore ' + name + "?",
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
                    document.getElementById("frmHiden").submit();
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Khôi phục thành công',
                        showConfirmButton: false,
                        timer: 1500
                    })
                } else if (result.isDenied) {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'Khôi phục thất bại',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            })
        }
    </script>
    <jsp:include page="layout/footer_js.jsp"></jsp:include>
</div>
</body>
</html>