<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Details</title>
</head>
<body>

<table>
<tr>
<th>Product Id</th><th>Product Name</th><th>Product Description</th><th>Product Category</th><th>Product Price</th><th>Brand</th>
</tr>
<c:forEach items="${productList}" var="product">
<tr>
<td><c:out value="${product.productId}"/></td>
<td><c:out value="${product.productName}"/></td>
<td><c:out value="${product.productDescription}"/></td>
<td><c:out value="${product.productCategory}"/></td>
<td><c:out value="${product.productPrice}"/></td>
<td><c:out value="${product.brand}"/></td>
</tr>

</c:forEach>
</table>

</body>
</html>