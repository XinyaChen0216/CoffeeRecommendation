package recommendation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recommendation.dal.ReviewsDao;
import recommendation.model.Reviews;

@WebServlet("/coffeeReviews")

public class FindReviews extends HttpServlet{
	
	public  ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        String coffeeName = req.getParameter("coffeeName");
        if (coffeeName == null || coffeeName.trim().isEmpty()) {
            messages.put("title", "Invalid coffeeName.");
        } else {
        	messages.put("title", "Reviews for " + coffeeName);
        }
        double averageRating = 0.0;
		try {
			averageRating = reviewsDao.getAverageRatingByCoffeeName(coffeeName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        List<Reviews> reviewsList = new ArrayList<Reviews>();
        try {
        	reviewsList = reviewsDao.getReviewsByCoffeeName(coffeeName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.setAttribute("averageRating", averageRating);

        req.setAttribute("reviewsList", reviewsList);
        req.setAttribute("coffeeName", coffeeName);
        req.getRequestDispatcher("/CoffeeReview.jsp").forward(req, resp);
	}
}