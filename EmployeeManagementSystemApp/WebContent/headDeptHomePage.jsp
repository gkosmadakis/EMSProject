<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="pragma" content="nocache">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
</head>
<body>
<% 
response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
String userName = (String) session.getAttribute("username");
System.out.println(userName);
if (null == userName) {
	System.out.println("null");
   request.setAttribute("Error", "Session has ended.  Please login.");
   RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
   rd.forward(request, response);

}
%>
<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h3> Head of Department Home Page </h3>

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
</div>

	<form action="/EmployeeManagementSystemApp/assignRegulations.jsp"> 
  		<input type="submit" value="Assign regulations to employee" style="height: 40px;width: 200px;cursor:pointer;border-radius: 5px;"/>
  	</form>



</body>	

</html>