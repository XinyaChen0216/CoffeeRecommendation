<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Professional</title>
</head>
<body>
	<h1>Create Professional</h1>
	<form action="professionalcreate" method="post">
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="">
		</p>
		<p>
			<label for="userpassword">Password</label>
			<input id="userpassword" name="userpassword" value="">
		</p>
		<p>
			<label for="email">Email</label>
			<input id="email" name="email" value="">
		</p>
		<p>
            <label for="phonenumber">Phone Number</label>
            <input id="phonenumber" name="phonenumber" value="">
        </p>
        <p>
            <label for="hascredential">Has Credential (true/false)</label>
            <input id="hascredential" name="hascredential" type="checkbox" ${param.hascredential ? 'checked' : ''}>
        </p>
        <p>
            <label for="experience">Experience (years)</label>
            <input id="experience" name="experience" value="">
        </p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>