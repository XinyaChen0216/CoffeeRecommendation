<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Coffees Within Price Range</title>
</head>
<body>
	<body> 

    <h1>Coffees Within Your Price Range</h1>
    <form action="coffeeWithInPrice" method="get">
       maxPrice: <input type= "number" name="maxPrice" required>
        <input type="submit" value="Search">
    </form>
        <table border="1">
            <tr>
                <th>CoffeeName</th>
                <th>RoastType</th>
                <th>Price</th>
                <th>RoasterName</th>
     
            </tr>
            <c:forEach var="coffee" items="${coffeesList}" >
                <tr>
                    <td><c:out value="${coffee.getCoffeeName()}" /></td>
                    <td><c:out value="${coffee.getRoastType()}" /></td>
                    <td><c:out value="${coffee.getPrice()}" /></td>
                    <td><c:out value="${coffee.getRoasterName()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>





