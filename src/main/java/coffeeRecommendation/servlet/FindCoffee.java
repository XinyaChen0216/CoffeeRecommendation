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
import recommendation.model.Coffee;
import recommendation.model.Reviews;

@WebServlet("/findCoffee")
public class FindCoffee extends HttpServlet{
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
		
        String keywords= req.getParameter("keywords");
        if (keywords == null || keywords.trim().isEmpty()) {
            messages.put("title", "Invalid keywords.");
        } else {
        	messages.put("title", "Coffees for " + keywords);
        }
		

        List<Reviews> coffees = new ArrayList<Reviews>();
        try {
        	coffees = reviewsDao.getReviewBykeywords(keywords);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }

        req.setAttribute("coffees", coffees);
        req.setAttribute("keywords", keywords);
        req.getRequestDispatcher("/FilterCoffee.jsp").forward(req, resp);
	}

}