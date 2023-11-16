package recommendation.servlet;

import recommendation.dal.*;
import recommendation.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/professionalupdate")
public class ProfessionalUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Professionals professional = professionalsDao.getProfessionalFromUserName(userName);
        		if(professional == null) {
        			messages.put("success", "UserName does not exist.");
        		}
        		req.setAttribute("professional", professional);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProfessionalUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserName.");
        } else {
        	try {
        		Professionals professional = professionalsDao.getProfessionalFromUserName(userName);
        		if(professional == null) {
        			messages.put("success", "UserName does not exist. No update to perform.");
        		} else {
        			String newPhoneNumber = req.getParameter("phonenumber");
        			if (newPhoneNumber == null || newPhoneNumber.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid phone number.");
        	        } else {
        	        	professional = professionalsDao.updatePhoneNumber(professional, newPhoneNumber);
        	        	messages.put("success", "Successfully updated " + userName);
        	        }
        		}
        		req.setAttribute("professional", professional);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProfessionalUpdate.jsp").forward(req, resp);
    }
}
