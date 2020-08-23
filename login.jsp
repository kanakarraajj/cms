<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
<table align="center">
<tr>
<td>
<%

if ( request.getAttribute("status") != null) { %>

 <font color="red"> <b><%=request.getAttribute("status").toString()%> </b></font>

<% } %>
</td>

</tr>
<tr>
<td>
<br> <b>Please enter username & password as test/test </b>

</td>
</tr>
</table>

<form action="/cms/LoginServlet" method="post">  
	<table align="center">
		<tr>
		<td>user name:  <input type="text" name="username" value=""></td>
		</tr>
		<tr>
		<td>password:  <input type="password" name="password" value=""></td>
		</tr>
		
		<tr>
		<td><input type="submit" name="login" ></td>
		</tr>
	</table>
</form>

</body>
</html>