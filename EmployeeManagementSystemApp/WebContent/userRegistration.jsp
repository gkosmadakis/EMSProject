<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Login</title>
</head>
<body>
 
 <h1>
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
   </div>
 </h1>
 
   <form action="/EmployeeManagementSystemApp/userRegistration" id="registerID" method="post">
<fieldset> <legend>Registration Form</legend>
	
	<div><label for="firstname">Firstname</label><input type="text" required 
	placeholder="Enter your firstname"name="firstname"></div><br>
	
	<div><label for="lastname">Lastname</label><input type="text" required 
	placeholder="Enter your lastname"name="lastname"></div><br>	
	
	<div><label for="dateofbirth">Date of Birth</label><input type="text" required 
		placeholder="mm/dd/yyyy" name="dateofbirth"></div><br>
		
 	<div><label for="department">Department</label><input type="text" required 
	placeholder="Enter your department"name="department"></div><br>
	
	<div><label for="email">Email</label><input type="email" required 
	placeholder="Enter your email" name="email"></div><br>
	
	<div><label for="username">Username</label><input type="text" required 
	placeholder="Enter your username" name="username"></div><br>
	
	<div><label for="password">Password</label><input type="password" required 
	placeholder="Enter your password" name="password"></div><br>
	
	<div><label for="password">Confirm Password</label><input type="password" required 
	placeholder="Enter your password" name="confPassword"></div><br>
	
	<div><label for="role">Role</label><select name="role" required>
	<option value="admin">Admin</option>
	<option value="employee">Employee</option>
	<option value="headOfDepartment">Head of Department</option></select>
	</div><br><br>
	
	<p class="submit"> <input type="submit" name="submit" value="Register" style="cursor:pointer;"></p></fieldset>
	
</form>

 </div>
</body>
</html>