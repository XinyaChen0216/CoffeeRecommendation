package recommendation.tools;

import recommendation.dal.*;
import recommendation.model.*;

import java.sql.SQLException;
import java.util.Date;
//import java.util.List;


public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PersonsDao personsDao = PersonsDao.getInstance();
		AdminsDao adminDao = AdminsDao.getInstance();
		ProfessionalsDao professionalsDao = ProfessionalsDao.getInstance();
		CoffeeLoversDao coffeeLoversDao = CoffeeLoversDao.getInstance();

		
		// INSERT objects from our model.
		Persons person = new Persons("taylor", "425bdhgd!", "aaaa12@gmail.com", "4256888777");
		person = personsDao.create(person);
		Persons person1 = new Persons("black", "978fudhgd!", "bbbb12@gmail.com", "4256808077");
		person1 = personsDao.create(person1);
		Persons person2 = new Persons("joe", "9gn86dhgd!", "mmmm12@gmail.com", "4256959577");
		person2 = personsDao.create(person2);
		
		Date date = new Date();
		Admins admin = new Admins("taylor0", "42mthmfggd!", "aaaaa12@gmail.com", "4256888777", date);
		admin = adminDao.create(admin);
		Admins admin1 = new Admins("taylor1", "925bdhgd!", "aabb12@gmail.com", "4256888788", date);
		admin1 = adminDao.create(admin1);
		Admins admin2 = new Admins("taylor2", "926bdhgd!", "aacc12@gmail.com", "5256888788", date);
		admin2 = adminDao.create(admin2);
		
		Professionals professional = new Professionals("black0", "978fudhgd!", "bbbbb12@gmail.com", "4256808077", 
				true, 5, Professionals.StatusLevel.advanced);
		professional = professionalsDao.create(professional);
		Professionals professional1 = new Professionals("black1", "979fudhgd!", "bbbcc12@gmail.com", "4256808177", 
				true, 3, Professionals.StatusLevel.intermediate);
		professional1 = professionalsDao.create(professional1);
		Professionals professional2 = new Professionals("black2", "97hetdhgd!", "bbbff12@gmail.com", "4256848177", 
				true, 2, Professionals.StatusLevel.novice);
		professional2 = professionalsDao.create(professional2);
		
		CoffeeLovers coffeeLover = new CoffeeLovers("joe0", "9gn86dhg74!", "mmghfm12@gmail.com", "4257959577", 5, 
				CoffeeLovers.StatusLevel.advanced);
		coffeeLover = coffeeLoversDao.create(coffeeLover);
		CoffeeLovers coffeeLover1 = new CoffeeLovers("joe1", "9gnt3dhgd!", "mmmnfm12@gmail.com", "4256989577", 2, 
				CoffeeLovers.StatusLevel.intermediate);
		coffeeLover1 = coffeeLoversDao.create(coffeeLover1);
		CoffeeLovers coffeeLover2 = new CoffeeLovers("joe2", "9gnerdhgd!", "mrnfm12@gmail.com", "4266989577", 1, 
				CoffeeLovers.StatusLevel.novice);
		coffeeLover2 = coffeeLoversDao.create(coffeeLover2);
		

		// READ.
		Persons persontest = personsDao.getPersonFromUserName("taylor");
        if (persontest != null) {
            System.out.println("Person fetched: " + persontest.getUserName());
        } else {
            System.out.println("Person not found.");
        }
        
        Persons persontest2 = personsDao.getPersonFromUserName("taylor");
        if (persontest2 != null) {
        	persontest2 = personsDao.updatePhoneNumber(persontest2, "9999999999");
            System.out.println("Person phone number updated: " + persontest2.getUserName());
        } else {
            System.out.println("Person not found for update.");
        }
        
        Persons persontest3 = personsDao.getPersonFromUserName("taylor");
        if (persontest3 != null) {
            personsDao.delete(persontest3);
            System.out.println("Person deleted: " + persontest3.getUserName());
        } else {
            System.out.println("Person not found for deletion.");
        }
		
        
		Admins admintest = adminDao.getAdminFromUserName("taylor0");
        if (admintest != null) {
            System.out.println("Admin fetched: " + admintest.getUserName());
        } else {
            System.out.println("Admin not found.");
        }
         
        admin = adminDao.updatePhoneNumber(admin, "9999999999");
        System.out.println("Admin phone number updated: " + admin.getUserName());
         
        adminDao.delete(admin);
        System.out.println("Admin deleted: " + admin.getUserName());
		
        
        Professionals professionaltest = professionalsDao.getProfessionalFromUserName("black0");
        if (professionaltest != null) {
            System.out.println("Professional fetched: " + professionaltest.getUserName());
        } else {
            System.out.println("Professional not found.");
        }
        
        professional = professionalsDao.updatePhoneNumber(professional, "9999999999");
        System.out.println("Professional phone number updated: " + professional.getUserName());
        
        professionalsDao.delete(professional);
        System.out.println("Professional deleted: " + professional.getUserName());
        
        
        
        CoffeeLovers coffeeLovertest = coffeeLoversDao.getCoffeeLoverFromUserName("joe0");
        if (coffeeLovertest != null) {
            System.out.println("CoffeeLover fetched: " + coffeeLovertest.getUserName());
        } else {
            System.out.println("CoffeeLover not found.");
        }
        
        coffeeLover = coffeeLoversDao.updatePhoneNumber(coffeeLover, "9999999999");
        System.out.println("CoffeeLover phone number updated: " + coffeeLover.getUserName());
        
        coffeeLoversDao.delete(coffeeLover);
        System.out.println("CoffeeLover deleted: " + coffeeLover.getUserName());
	}
}
