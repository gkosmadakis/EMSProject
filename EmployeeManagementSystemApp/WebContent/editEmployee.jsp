<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<h3> Edit Employee</h3>

<% String id = (String) request.getAttribute("id");
   String username = (String)request.getAttribute("username");
   String department = (String) request.getAttribute("department");
   String email = (String)request.getAttribute("email");
   String firstname = (String) request.getAttribute("firstname");
   String lastname = (String)request.getAttribute("lastname");
   String role = (String) request.getAttribute("role");
   String dateofbirth = (String)request.getAttribute("dateofbirth");
   String password = (String)request.getAttribute("password");
   %>

<form method=post action="/EmployeeManagementSystemApp/ViewEmployeesListController"> 

<table>
<tr>
<td><label for="id">ID </label><input type="text" name="id" value="<%=id%>"> </td> </tr>
<tr>
<td><label for="username">Username </label><input type="text" name="username" value="<%=username%>"></td></tr>
<tr>
<td><label for="department">Department </label><input type="text" name="department" value="<%=department%>"></td></tr>
<tr>
<td><label for="email">Email </label><input type="text" name="email" value="<%=email%>"></td></tr>
<tr>
<td><label for="fisrtname">Firstname </label><input type="text" name="firstname" value="<%=firstname%>"></td></tr>
<tr>
<td><label for="lastname">Lastname </label><input type="text" name="lastname" value="<%=lastname%>"></td></tr>
<tr> 
<td><label for="role">Role </label><input type="text" name="role" value="<%=role%>"></td></tr>
<tr>
<td><label for="dateofbirth">Date of Birth </label><input type="text" name="dateofbirth" value="<%=dateofbirth%>"></td></tr>

<input type="hidden" id="password" name="password" value="<%=password %>">

</table>

<p class="submit"> <input type="submit" name="submit" value="Update"></p>

</form>
</body>
</html>