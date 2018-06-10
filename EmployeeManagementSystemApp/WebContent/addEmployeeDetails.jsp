<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.ems.controller.DeleteDepartmentController" %>
<%@page import="com.session.SessionManagement" %>
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
function checkIfEmployeeIsYoungerThan24() {
	
	var date = new Date();
	date.setFullYear( date.getFullYear() - 24 );
    
	var x = document.getElementById("dob").value;
    var dateEntered = new Date(x);
   
    if (dateEntered > date) {
    	
    	alert("According to the date of birth you entered the employee is younger than 24 years old and cannot be entered in the system.");
    	document.getElementById('dob').value = '';
    }
}
</script>

</head>

<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<%
List<String> list = DeleteDepartmentController.getAllDepartmentsFromDB();
int size = list.size();
%>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<form method=post action="/EmployeeManagementSystemApp/addEmployeeController"> <fieldset> <p> Add Employee Details </p>
	<div><label for="firstname">Firstname</label><input type="text" required name='firstname' placeholder="Enter Firstname"></div><br> 
	<div><label for="lastname">Lastname</label><input type="text" required name='lastname' placeholder="Enter Lastname"></div><br>
	<div><label for="dateofbirth">Date of Birth</label><input type="text" required 
		placeholder="mm/dd/yyyy" name="dateofbirth" id="dob" oninput="checkIfEmployeeIsYoungerThan24()"></div><br>
	<div><label for="username">Username</label><input type="text" required  
		placeholder="Enter username"name="username"></div><br>
		<div><label for="password">Password</label><input type="password" required  
		placeholder="Enter password"name="password"></div><br>
	<div><label for="email">Email</label><input type="email" required  
		placeholder="Enter email"name="email"></div><br>
		
	<div><label for="email">Department</label><select name="department">
		<option value="" label="Select"/>
		<%
			for (int i=0;i<size;i++) {
  			String s = (String)list.get(i);
		%>
			<option value="<%=s%>" ><%=s%>
			</option>
		<%
		}
		%>
		</select> <br> <br>
	
	<div><label for="role">Role</label><select name="role" required>
	<option value="" label="Select"/>
	<option value="admin">Admin</option>
	<option value="employee">Employee</option>
	<option value="headOfDepartment">Head of Department</option></select>
	</div><br>
	
	<p class="submit"> <input type="submit" name="submit" value="Add" style="cursor:pointer;"></p></fieldset></form>
			
	
			
</body>
</html>