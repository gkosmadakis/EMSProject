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

<h3> Employee Home Page </h3>

<form action="/EmployeeManagementSystemApp/GetEmployeeDetailsController">
<input type="submit" value="My Details" style="height: 40px;width: 90px;cursor:pointer;border-radius:5px;"/>
 </form> <br>
 
 <% session = request.getSession(false);
 if (session.getAttribute("employeeHasAssignedRegulation") != null){
 boolean employeeHasAssignedRegulation = (boolean) session.getAttribute("employeeHasAssignedRegulation");
 
 	if (employeeHasAssignedRegulation) {
 %>
 <h3> NOTIFICATIONS: </h3>
 <p> You have been assigned a Regulation! </p>
 <a href=/EmployeeManagementSystemApp/GetEmployeeDetailsController?assigned=<%=true%>>See Regulation</a>
 
 <%} } %>
 
</body>
</html>