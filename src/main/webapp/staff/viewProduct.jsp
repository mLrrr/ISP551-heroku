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
	<title>View Product</title>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">          
  		<a class="navbar-brand" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" class="active" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" href="RegisterController">Register User</a>
  		<a class="navbar-brand" href="LogoutController">Logout</a>       		
    </div>
  </nav>
</div>
<div class="container">
<br><br>
<h3>View Product</h3>
<br><br>
		<label for="pid">Product ID</label>: <c:out value="${product.pid}"/><br>
    	<label for="productname">Product Name</label>: <c:out value="${product.productname}"/><br>    	
    	<label for="price">Price</label>: <c:out value="${product.price}"/><br>	
      	<label for="quantity">Quantity</label>: <c:out value="${product.quantity}" /><br>    	
        <label for="sid">Supplier Id</label>: <c:out value="${product.sid}"/><br><br>   		     
        <a href="ProductController?action=listProduct" class="btn btn-warning">Product List</a>
        
</div>
</body>
</html>
<!-- Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
Date:12 January 2023 -->