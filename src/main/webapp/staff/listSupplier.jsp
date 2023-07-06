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
	<title>List of Suppliers</title>
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
<div class="container">
<br><br>
<h3>List of Suppliers</h3>
<br><br>
<table class="table table-striped" style="width:100%">
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Address</th>    
    <th>Phone No</th>
    <th>Email</th>
    <th colspan="3">Actions</th>
  </tr>
 <c:forEach items="${users}" var="user" varStatus="suppliers">
  <tr>
    <td><c:out value="${user.supplier.sid}" /></td>
    <td><c:out value="${user.supplier.name}" /></td>
    <td><c:out value="${user.supplier.address}" /></td>    
    <td><c:out value="${user.supplier.phoneno}" /></td>
    <td><c:out value="${user.email}" /></td>
    <td><a href="SupplierController?action=viewSupplier&sid=<c:out value="${user.supplier.sid}" />" class="btn btn-warning">View</a></td>
    <td><a href="SupplierController?action=updateSupplier&sid=<c:out value="${user.supplier.sid}" />" class="btn btn-primary">Update</a></td>    
    <td><input type="hidden" id="id-${suppliers.index}" value="<c:out value="${user.id}"/>"><button class="btn btn-danger" onclick="confirmation('${suppliers.index}')">Delete</button></td>
  </c:forEach>
</table>
</div>
<script>
		function confirmation(index){					  
			  var id = $("#id-" + index).val();			 
			  console.log(id);
			  var r = confirm("Are you sure you want to delete?");
			  if (r == true) {				 		  
				  location.href = 'SupplierController?action=deleteSupplier&id=' + id;
				  alert("Supplier successfully deleted");			
			  } else {				  
			      return false;	
			  }
		}
	</script>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</body>
</html>
