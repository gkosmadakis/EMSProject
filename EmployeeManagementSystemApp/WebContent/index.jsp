<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Employee Management System</title>

</head>
<body>


<div class="solidBorder">
<h1> Welcome to Employee Management System</h1>
<h3> The application can be used by Administrators to add and delete department, to add view edit and delete employees, to Edit the Department Head information, 
to add view and delete regulations. Employees can view their details, view any regulation assigned to them, add comments and accept or
decline the regulation. Head of Departments can assign regulation to their employees. If you don't have an account start by registering your details.

 </h3>
</div><br><br>

<h4>
  <div align="center">
   <% if(request.getAttribute("nonSuccess") != null) { %>
    <p style="color: red">
     <%= request.getAttribute("nonSuccess") %>
    </p>
   <% } %>
   <% if(request.getAttribute("success") != null) { %>
    <p style="color: green;">
     <%= request.getAttribute("success") %>
    </p>
   <% } %>
</h4>

<form action="login" method="post">
<fieldset> <legend>Login</legend>
    <div><label for="username">Username</label><input type="text" required name='username' placeholder="Enter your username"></div><br>
    <div><label for="password">Password</label><input type="password" required name='password' placeholder="Enter Password"></div>
    <p class="submit"> <input type="submit" value="Login" style="cursor:pointer;"> </p></fieldset>
</form>

<p>Are you a new user? Then click to <a href="userRegistration.jsp" method="get">Register here</a>
</body>
</html>