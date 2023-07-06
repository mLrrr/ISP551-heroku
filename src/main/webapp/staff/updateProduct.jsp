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
	<title>Update Product</title>
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
<div class="container"></div>
<div class="container">
<br><br>
<h3>Update Product</h3>
<br><br>
  <form action="ProductController" method="POST">
  <div class="mb-3">
    <label for="pid" class="form-label">Product Id</label>    
    <input type="text" class="form-control" id="pid" name="pid" value="<c:out value="${product.pid}"/>" readonly>   
  </div>     
  <div class="mb-3">
    <label for="productname" class="form-label">Product Name</label>    
    <input type="text" class="form-control" id="productname" name="productname" value="<c:out value="${product.productname}"/>">   
  </div>
  <div class="mb-3">
    <label for="price" class="form-label">Price</label>    
    <input type="text" class="form-control" id="price" name="price" value="<c:out value="${product.price}"/>">   
  </div>
  <div class="mb-3">
    <label for="quantity" class="form-label">Quantity</label>    
    <input type="number" class="form-control" id="quantity" name="quantity" value="<c:out value="${product.quantity}"/>">    
  </div>
  <div class="mb-3">
  <label for="suppliername" class="form-label">Supplier Name</label>    
    <select class="form-control" id="sid" name="sid">  
     	<c:forEach items="${suppliers}" var="supplier">
      		<option value="${supplier.sid}" ${supplier.sid == selectedSupplier ? 'selected="selected"': ''}><c:out value="${supplier.sid}"/>
      		-<c:out value="${supplier.name}"/></option>
      	</c:forEach>
    </select>
  </div>
  <div class="mb-3">
    <input type="submit" class="btn btn-primary" value="Submit"> 
    <input type="reset" class="btn btn-primary" value="Reset">  
  </div>
  </form>
</div>
</body>
</html>
<!-- Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
Date:12 January 2023 -->