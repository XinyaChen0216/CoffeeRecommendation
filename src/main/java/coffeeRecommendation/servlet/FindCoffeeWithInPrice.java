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

import recommendation.dal.CoffeeDao;
import recommendation.dal.ReviewsDao;
import recommendation.model.Coffee;

@WebServlet("/coffeeWithInPrice")

public class FindCoffeeWithInPrice extends HttpServlet{
	
	public  CoffeeDao coffeeDao;
	public  ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		coffeeDao = CoffeeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        Double maxPrice = null;
        List<Coffee> coffeesList = new ArrayList<>();
        
        
        String maxPriceStr = req.getParameter("maxPrice");
        if (maxPriceStr == null || maxPriceStr.trim().isEmpty()) {
            messages.put("title", "Invalid maxPrice.");
        } else {
            try {
                maxPrice = Double.parseDouble(maxPriceStr.trim());
                messages.put("title", "Coffees under price of " + maxPrice);

                try {
                    coffeesList = coffeeDao.getCoffeesWithInPrice(maxPrice);
                } catch (SQLException e) {
                    e.printStackTrace();
                    messages.put("error", "Error while accessing database.");
                    throw new IOException(e);
                }
            } catch (NumberFormatException e) {
                messages.put("title", "Invalid maxPrice format.");
            }
        }
        
        //if (maxPrice != null) {
            //req.setAttribute("coffeesList", coffeesList);
            //req.getRequestDispatcher("/FindCoffeewithInPrice.jsp").forward(req, resp);
        //} else {
            // Handle the case where maxPrice is invalid, maybe redirect to a different page or show an error message
            //req.getRequestDispatcher("/errorPage.jsp").forward(req, resp);
        //}
        req.setAttribute("coffeesList", coffeesList);
        req.getRequestDispatcher("/FindCoffeeWithInPrice.jsp").forward(req, resp);
	}
}
