<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="springcore" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<springform:form modelAttribute="product" method="post" action="addproduct.htm">
<table>
<tr>
<td><springform:label path="productId">Product Id:</springform:label>
<td><springform:input path="productId"/>
<springform:errors path="productId" style="color:red"></springform:errors>
</td>
</tr>
<tr>
<td><springform:label path="productName">Product Name:</springform:label>
<td><springform:input path="productName"/></td>
</tr>
<tr>
<td><springform:label path="productDescription">Product Decription:</springform:label>
<td><springform:input path="productDescription"/></td>
</tr>
<tr>
<td>
<springform:label path="productCategory">Product Category:</springform:label>
</td>
<td>
<springform:select path="productCategory">
<springform:options items="${categoryList}"/>
</springform:select>
<springform:errors path="productCategory" style="color:red"></springform:errors>

</td>
</tr>
<tr>
<td><springform:label path="productPrice">Product Price:</springform:label>
<td><springform:input path="productPrice"/></td>
</tr>
<tr>
<td><springform:label path="brand">Brand:</springform:label>
<td><springform:input path="brand"/></td>
</tr>
<tr>
<td><input type="submit" value="Add Product"></td>
</tr>
</table>

</springform:form>
${status}
</body>
</html>