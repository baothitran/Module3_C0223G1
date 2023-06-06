<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Discount Calculator</title>
</head>
<body>
<h2>Product Discount Calculator</h2>
<form action="/calculate" method="post">
    <label>Product Description</label><br>
    <input type="text" name="description" placeholder="Enter product description"><br>
    <label>List Price</label><br>
    <input type="number" name="price" placeholder="Enter list price"><br>
    <label>Discount Percent</label><br>
    <input type="number" name="discount" placeholder="Enter discount percent"><br>
    <input type="submit" id="submit" value="Calculate">
</form>
</body>
</html>