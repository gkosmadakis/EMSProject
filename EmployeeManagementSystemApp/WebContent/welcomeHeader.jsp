<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table width="100%">
 
 <tr bgcolor=F9F9F3>
  <td width="70%"><img src='images/header.jpg' style='width:100%;height:30%' alt='[]'/> </td>
  <td align="right"> 
  	
  	<h2> 			
   		Hi, <%= session.getAttribute("username") %> <br>
   </h2>
   
   <h3>
   		You are <%= session.getAttribute("role") %> 
   </h3>
   
  <h3 style="text-align: right;"><a href="logout.jsp"> Logout</a>
  </h3>
  
  </td>
 </tr>
 
 </table>
</body>
</html>