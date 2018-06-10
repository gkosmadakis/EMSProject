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

<h3> Add Regulation Page </h3>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<form method=post action="/EmployeeManagementSystemApp/AddRegulationController"> <fieldset> <p> Add a new Regulation </p>
	<div><label for="department">Department Name</label><input type="text" required name='department' placeholder="Enter name"></div>
	<br> 
	<div><label for="departmentHead">Department Head</label><input type="text" required name='departmenthead' placeholder="Enter Head name"></div>
	<br>
	<div><label for="createdate">Creation Date</label><input type="date" required name='createdate' placeholder="Enter Creation Date"></div>
	<br> 
	<div><label for="regulationtitle">Regulation Title</label><input type="text" required name='regulationtitle' placeholder="Enter Regulation title"></div>
	<br>
	<div><label for="regulationtext">Regulation Text</label><textarea rows="4" cols="45" required name='regulationtext' 
	placeholder="Enter text Regulation"></textarea></div>
	<br>

	<p class="submit"> <input type="submit" name="submit" value="Add" style="cursor:pointer;"></p></fieldset>
</form>
	
	
</body>


</html>