<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user-form</title>
  <link rel="stylesheet" href="jquery-ui.css"/>
  <link rel="stylesheet" href="style.css"/>
  <script src="jquery1.12.4.js"></script>
  <script src="jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</head>
<body>

<form action="/cms/FormServlet" method="post"> 
<input type="hidden" name="operation" value="add" > 
	<table align="center" border="1" width="80%">
		<tr align="center" height="50px">
		<td colspan="2">
			<H3> Create NEW CMS Form</H3> 
			
			<%
			if ( request.getAttribute("status") != null) { %>
			 <font color="green"> <b><%=request.getAttribute("status").toString()%> </b></font>
			<% } %>

		</td>
		</tr>
		<tr height="100px">
		<td>Name: <input type="text" name="name" value="" size="55"> <br>
			Password: <input type="password" name="password" value="" size="50"></td>
		<td>DOB: <input type="text" name="dob" id="datepicker"></td>
		</tr>
		<tr height="100px">
		<td>Married:  
			<input type="radio" name="married" value="Yes" checked> Yes 
  			<input type="radio" name="married" value="No"> No<br>
		</td>
		
		<td>Qualification: 
		  <select name="qualification">
			  <option value="BTech" selected>BTech</option>
			  <option value="BE">BE</option>
			  <option value="MBA">MBA</option>
			  <option value="BSc">BSc</option>
			</select> 
		</td>
		</tr>
		
		<tr align="center" height="50px">
		<td>
			<input type="submit" value="Submit Form Details" >
			<input type="reset" value="Clear" > 
		</td>
		<td> <a href="/cms/list_search.jsp">Search CMS Form Data</a> <br> 
			<a href="/cms/login.jsp">Login</a>  </td>
		</tr>
	</table>
</form>

</body>
</html>