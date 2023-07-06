<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%  
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);

  %>     
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<title>View Supplier</title>
</head>
<body>

<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">                     	  		   		
  		<a class="navbar-brand" href="LogoutController">Logout</a>       		
    </div>
  </nav>
</div>
<div class="container">
	Welcome <b> <c:out value="${user.email}" /> </b>
	
	
<br><br>
<h3>Supplier Profile</h3>
<br><br>
		<label for="id">Supplier ID</label>: <c:out value="${supplier.sid}"/><br>
    	<label for="name">Name</label>: <c:out value="${supplier.name}"/><br>    	
    	<label for="address">Address</label>: <c:out value="${supplier.address}"/><br>	
      	<label for="city">City</label>: <c:out value="${supplier.city}" /><br>    	
        <label for="postcode">Postcode</label>: <c:out value="${supplier.postcode}"/><br>   		
        <label for="state">State</label>: <c:out value="${supplier.state}" /><br>    	
        <label for="country">Country</label>: <c:out value="${supplier.country}"/><br>    	        
        <label for="phoneno">Phone No</label>: <c:out value="${supplier.phoneno}"/><br><br>                                      
</div>
</body>
</html>
<!-- Author: Fadilah Ezlina Shahbudin (fadilahezlina@uitm.edu.my)
Date:12 January 2023 -->