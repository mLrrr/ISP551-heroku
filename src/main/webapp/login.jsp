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
	<title>Login</title>
</head>
<body>
<div class="container">
<br><br>
<h3>Login</h3>
<br><br>
<form action="LoginController" method="POST">
 <div class="mb-3">
    <label for="email" class="form-label">Email</label>    
    <input type="email" class="form-control" id="email" name="email" placeholder="Email.." required>   
  </div>
  <div class="mb-3">
    <label for="password" class="form-label">Password</label>    
    <input type="password" class="form-control" id="password" name="password" placeholder="Password.." required>   
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