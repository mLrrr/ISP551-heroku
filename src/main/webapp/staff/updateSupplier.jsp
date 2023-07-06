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
	<title>Update Supplier</title>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">              
  		<a class="navbar-brand" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" class="active" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" href="RegisterController">Register User</a>
  		<a class="navbar-brand" href="LogoutController">Logout</a>       		
    </div>
  </nav>
</div>
<div class="container"></div>
<div class="container">
<br><br>
<h3>Update Supplier</h3>
<br><br>
  <form action="SupplierController" method="POST">
  <div class="mb-3">
    <label for="sid" class="form-label">Supplier Id</label>
        <input type="text" class="form-control" name="sid" value="<c:out value="${supplier.sid}"/>" readonly/> 
  </div> 
    <div class="mb-3">
    <label for="name" class="form-label">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="<c:out value="${supplier.name}"/>">    
  </div>
  <div class="mb-3">
    <label for="address" class="form-label">Address</label>
    <input type="text" class="form-control" id="address" name="address" value="<c:out value="${supplier.address}"/>">   
  </div>
  <div class="mb-3">
    <label for="city" class="form-label">City</label>
    <input type="text" class="form-control" id="city" name="city" value="<c:out value="${supplier.city}"/>">   
  </div>
   <div class="mb-3">
    <label for="postcode" class="form-label">Postcode</label>
    <input type="text" class="form-control" id="postcode" name="postcode" value="<c:out value="${supplier.postcode}"/>">
  </div>
   <div class="mb-3">
    <label for="state" class="form-label">State</label>
    <input type="text" class="form-control" id="state" name="state" value="<c:out value="${supplier.state}"/>">
  </div>
   <div class="mb-3">
    <label for="country" class="form-label">Country</label> 
    	<input type="text" class="form-control" id="country" name="country" value="<c:out value="${supplier.country}"/>">
	</div>
   <div class="mb-3">
    <label for="phoneno" class="form-label">Phone No</label>
        <input type="text" class="form-control" id="phoneno" name="phoneno" value="<c:out value="${supplier.phoneno}"/>">
  </div>
    <div class="mb-3">    
        <input type="hidden" class="form-control" name="id" value="<c:out value="${supplier.id}"/>"/> 
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