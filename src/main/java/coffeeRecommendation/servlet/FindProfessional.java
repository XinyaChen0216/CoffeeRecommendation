package recommendation.servlet;

import recommendation.dal.*;
import recommendation.model.*;
//import recommendation.model.Professionals.StatusLevel;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUser is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /finduser
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 */
@WebServlet("/findprofessional")
public class FindProfessional extends HttpServlet {
	
	protected ProfessionalsDao professionalsDao;
	
	@Override
	public void init() throws ServletException {
		professionalsDao = ProfessionalsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Professionals professional = null;
        
        // Retrieve and validate name.
        // userName is retrieved from the URL query string.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Professional, and store as a message.
        	try {
            	professional = professionalsDao.getProfessionalFromUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousUserName", userName);
        }
        req.setAttribute("professional", professional);
        
        req.getRequestDispatcher("/FindProfessional.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Professionals professional = null;
        
        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Professional, and store as a message.
        	try {
        		professional = professionalsDao.getProfessionalFromUserName(userName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userName);
        }
        req.setAttribute("professional", professional);
        
        req.getRequestDispatcher("/FindProfessional.jsp").forward(req, resp);
    }
}
