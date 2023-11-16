<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Professional</title>
</head>
<body>
	<form action="findprofessional" method="post">
		<h1>Search for a Professional by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="professionalCreate"><a href="professionalcreate">Create Professional</a></div>
	<br/>
	<h1>Matching Professional</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>UserPassword</th>
                <th>Email</th>
                <th>PhoneNumber</th>
                <th>HasCredential</th>
                <th>Experience</th>
                <th>Delete Professional</th>
                <th>Update Professional</th>
            <tr>
			    <td><c:out value="${professional.userName}" /></td>
			    <td><c:out value="${professional.userPassword}" /></td>
			    <td><c:out value="${professional.email}" /></td>
			    <td><c:out value="${professional.phoneNumber}" /></td>
			    <td><c:out value="${professional.hasCredential}" /></td>
			    <td><c:out value="${professional.experience}" /></td>
			    <td><a href="professionaldelete?username=${fn:escapeXml(professional.userName)}">Delete</a></td>
			    <td><a href="professionalupdate?username=${fn:escapeXml(professional.userName)}">Update</a></td>
			</tr>
       </table>
</body>
</html>
