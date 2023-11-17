<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Coffee By Roaster Name</title>
</head>
<body>
	<form action="findCoffeeByRoaster" method="get">
		<h1>Search coffee by roasterName</h1>
        Roaster Name: <input type="text" name="roasterName" required>
        <input type="submit" value="Search">
	</form>

	<h1>Matching Coffee</h1>
        <table border="1">
            <tr>
                <th>CoffeeName</th>
                <th>RoastType</th>
                <th>Price</th>
                <th>RoasterName</th>
            </tr>
            <c:forEach var="coffee" items="${coffees}" >
                <tr>
                    <td>${coffee.coffeeName}</td>
                    <td>${coffee.roastType}</td>
                    <td>${coffee.price}</td>
                    <td>${coffee.roasterName}</td>
                </tr>
            </c:forEach>
       </table>
	<h1>Average Rating:</h1>
    <h4>${averageRating}</h4>

</body>
</html>
