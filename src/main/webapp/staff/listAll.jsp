<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);
  
  if(session.getAttribute("sessionEmail")==null)	 
      response.sendRedirect("/SupplierMVC/login.jsp");
  %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">	
	<title>List All</title>	
</head>
<body>

<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">            
  		<a class="navbar-brand" class="active" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" class="active" href="RegisterController">Register User</a>
  		<a class="navbar-brand" href="LogoutController">Logout</a>       		
    </div>
  </nav>
</div>
<div class="container"></div>
<div class="container">
	<%	String email = (String)session.getAttribute("sessionEmail");%>
	Welcome <b> <%= email %> </b>
</div>
<div class="container">
<br><br>
<h3>List of Suppliers and Products</h3>
<br><br>
<table id="list" class="table table-striped" style="width:100%">
  <tr>
    <th>Supplier Id</th>
    <th>Supplier Name</th>
    <th>Product Id</th>
    <th>Product Name</th>
    <th>Quantity</th>
    <th colspan="2">Actions</th>
  </tr>
 <c:forEach items="${products}" var="product">
  	<tr>
    <td><c:out value="${product.supplier.sid}" /></td>
    <td><c:out value="${product.supplier.name}" /></td>
    <td><c:out value="${product.pid}" /></td>
    <td><c:out value="${product.productname}" /></td>  
    <td><c:out value="${product.quantity}" /></td>   
    <td><a href="SupplierController?action=viewSupplier&sid=<c:out value="${product.supplier.sid}" />" class="btn btn-primary">View Supplier</a></td>
    <td><a href="ProductController?action=viewProduct&pid=<c:out value="${product.pid}" />" class="btn btn-primary">View Product</a></td>     
     </tr>	   
    </c:forEach>       
</table>
</div>
<script>
$(document).ready(function() {
    $('#list').DataTable();
} );
</script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
<!-- Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
Date:12 January 2023 -->