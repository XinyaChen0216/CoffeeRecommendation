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
	<body>
	 <h1>Search Reviews By Coffee Name</h1>

    <form action="coffeeReviews" method="get">
        Coffee Name: <input type="text" name="coffeeName" required>
        <input type="submit" value="Search">
    </form>
    <h1>Reviews for Coffee: ${coffeeName}</h1>
    <c:if test="${not empty reviewsList}">
        <table border="1">
            <tr>
                <th>Review ID</th>
                <th>Created</th>
                <th>Content</th>
                <th>User Name</th>
                <th>Rating</th>
            </tr>
            <c:forEach var="review" items="${reviewsList}">
                <tr>
                    <td>${review.reviewID}</td>
                    <td>${review.created}</td>
                    <td>${review.content}</td>
                    <td>${review.userName}</td>
                    <td>${review.rating}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty reviewsList}">
        <p>No reviews found for coffee: ${coffeeName}</p>
    </c:if>
</body>
</body>
</html>