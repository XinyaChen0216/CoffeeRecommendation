package recommendation.servlet;

import recommendation.dal.*;
import recommendation.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findCoffeeByRoaster")
public class FindCoffeeByRoaster extends HttpServlet {
  
  protected RoasterDao roasterDao;
  
  @Override
  public void init() throws ServletException {
    roasterDao = RoasterDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Coffee> coffees = new ArrayList<Coffee>();
        double averageRating = 0.0;
    
    // Retrieve and validate Roaster Name.
        String roasterName = req.getParameter("roasterName");
        if (roasterName == null || roasterName.trim().isEmpty()) {
            messages.put("title", "Invalid roaster name.");
        } else {
        	messages.put("title", "Coffee for Roaster Name: " + roasterName);
        	CoffeeDao coffeeDao = CoffeeDao.getInstance();
            ReviewsDao reviewDao = ReviewsDao.getInstance();
            
            try {
              averageRating = reviewDao.getAverageRatingByRoasterName(roasterName);
              coffees = coffeeDao.getCoffeeByRoaster(roasterName);
            } catch (SQLException e) {
            	e.printStackTrace();
            	throw new IOException(e);
            }
          
        }
        
        // Retrieve Coffee, and store in the request.
        
        req.setAttribute("coffees", coffees);
        req.setAttribute("averageRating", averageRating);
        req.getRequestDispatcher("/FindCoffeeByRoaster.jsp").forward(req, resp);
  }
}
