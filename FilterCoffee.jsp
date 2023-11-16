<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Coffee Reviews</title>
</head>

 
<body>
	   <h1>Search for the type of Coffee that you want from Reviews</h1>

    <form action="findCoffee" method="get">
        <label for="keywords">Enter Keywords you want for the coffee:</label>
        <input type="text" id="keywords" name="keywords" required>
        <button type="submit">Search</button>
    </form>
<table border="1" ">
        <thead>
            <tr>
                <th>coffee name</th>
                <th>coffee review</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the reviewsList and display each review -->
            <c:if test="${not empty coffees}">
            <c:forEach var="coffee" items="${coffees}">
                <tr>
                    <td>${coffee.coffeeName}</td>
                    <td>${coffee.content}</td>
                </tr>
            </c:forEach>
            </c:if>
        </tbody>
    </table> 
   <%--  <c:if test="${not empty coffees}">
        <h2>Search Results:</h2>
        <c:forEach var="coffee" items="${coffees}">
            <p>${coffee.coffeeName}, content: ${coffee.content}</p>
        </c:forEach>
    </c:if> --%>
    
</body>
</html>