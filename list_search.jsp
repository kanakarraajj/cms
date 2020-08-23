<%@ page import="java.util.*" %>
<%@ page import="source.FormData" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>list_search</title>
</head>
<body>

<%
	List<FormData> results = null;

	if ( request.getAttribute("searchformresults") != null) {  
	 results = (ArrayList) request.getAttribute("searchformresults") ;
} %>

<br><br>
<table border="1"  align="center">
<tr>
<td align="center">
<h1><b> List Search CMS Forms</b></h1>
</td>
<td>
<a href="/cms/user-form.jsp">Create NEW CMS Form Data</a>
</td>
</tr>
<tr>
<td align="center">
<%
	if ( request.getAttribute("status") != null) { %>
	 <font color="green"> <b><%=request.getAttribute("status").toString()%> </b></font>
	<% } %>
</td>
</tr>
<tr>
<td>
<form action="/cms/FormServlet" method="post"> 
<input type="hidden" name="operation" value="search" > 
Name Search: <input type="text" name="name" value="" size="55"> &nbsp;&nbsp;
<input type="submit" value="Search" >
</form>

</td>
</tr>

<%  if( results == null || results.size() ==0 ) { %>
<tr>
<td>
	<h4 align="center">No Data Found. Please Search Using Name</h4>
</td>
</tr>
<% }  else  { %>
</table>
<br><br> 
<table border="1"  align="center">
<tr>
<td width="50px" align="center">ID</td>
<td width="500px" align="center">Name</td>
<td width="155px" align="center">DOB</td>
<td width="100px" align="center">Married</td>
<td width="200px" align="center">Qualification</td>
<td width="200px" align="center">Action</td> 
</tr>
<% for (int i=0; i< results.size(); i++) {
	FormData fd= (FormData)results.get(i);
%>
<tr align="center">
<td><%=fd.getId()%> </td>
<td><%=fd.getName()%> </td>
<td><%=fd.getDob()%> </td>
<td><%=fd.getMarried()%> </td>
<td><%=fd.getQualification()%> </td>
<td  align="center"><a href="/cms/FormServlet?operation=update-form&id=<%=fd.getId()%>&name=<%=fd.getName()%>&password=<%=fd.getPassword()%>&dob=<%=fd.getDob()%>&married=<%=fd.getMarried()%>&qualn=<%=fd.getQualification()%>">Edit</a>  
 &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; 
 <a href="/cms/FormServlet?operation=delete&id=<%=fd.getId()%>">Delete</a></td>
</tr>

<% }  %>

<% }  %>
</table>
</body>
</html>