<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CoffeeRecommendation Main Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&display=swap" rel="stylesheet">
    <style>
		body {
		    background-image: url('resources/background.jpg');
		    background-size: cover; 
		    background-position: center; 
		    background-repeat: no-repeat;
		    margin: 0;
		    padding: 0;
		}
		div {
			text-align: center;
			color: white;
		}
		h1{
			font-size: 52px;
        	font-family: 'Dancing Script', cursive; 
    	}
		p {
	        font-size: 28px;
	        font-family: 'Dancing Script'; 
    	}
		nav ul {
		    background-color: white;
		    padding: 2em;
		    list-style-type: none; 
		    margin: 2;
		    text-align: center;
		    font-size: 20px;
		}
		nav li {
			padding: 0.3em;
		}
		nav a {
		    text-decoration: none;
		    color: #000;
		}
		footer {
		    text-align: center;
		    position: absolute;
		    bottom: 0;
		    width: 100%;
		    background-color: rgba(255, 255, 255, 0.5);
		}
    </style>
</head>

<body>
	<div>
		<h1>Welcome to Bean Here For You!</h1>
	    <p>Find your perfect coffee match.</p>
	</div>
    

    <!-- Navigation buttons -->
    <nav>
        <ul>
            <li><a href="FindCoffeeByRoaster.jsp">Find Coffee by Roaster</a></li>
            <li><a href="FindCoffeeWithInPrice.jsp">Find Coffee by Price</a></li>
            <li><a href="CoffeeReview.jsp">Find Coffee Reviews</a></li>
            <li><a href="FilterCoffee.jsp">Find Coffee by Your Interest</a></li>
            <li><a href="FindProfessional.jsp">Find a Professional</a></li>
        </ul>
    </nav>

    <!-- Footer or additional content can be added here -->
    <footer>
        <p>&copy; 2023 CoffeeRecommendation</p>
    </footer>
</body>
</html>
