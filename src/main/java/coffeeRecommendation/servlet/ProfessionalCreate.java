package recommendation.servlet;

import recommendation.dal.*;
//import recommendation.model.*;

import java.io.IOException;
import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recommendation.model.Professionals;


@WebServlet("/professionalcreate")
public class ProfessionalCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/ProfessionalCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the Professional.
        	 String userPassword = req.getParameter("userpassword");
             String email = req.getParameter("email");
             String phoneNumber = req.getParameter("phonenumber");
             String hasCredentialParam = req.getParameter("hascredential");
             boolean hasCredential = hasCredentialParam != null && hasCredentialParam.equals("on");
             int experience = Integer.parseInt(req.getParameter("experience"));
             
             try {
            	    experience = Integer.parseInt(req.getParameter("experience"));
            	    if (experience < 0) {
            	        messages.put("experience", "Experience cannot be negative.");
            	        // Handle negative experience
            	    }
            	} catch (NumberFormatException e) {
            	    messages.put("experience", "Invalid format for experience.");
            	    // Handle invalid experience format
            	}
        	
	        try {
	        	Professionals professional = new Professionals(userName, userPassword, email, phoneNumber, hasCredential, experience, 
	        			Professionals.StatusLevel.novice);
	            professional = professionalsDao.create(professional);
	            messages.put("success", "Successfully created professional with username " + userName);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ProfessionalCreate.jsp").forward(req, resp);
    }
}
