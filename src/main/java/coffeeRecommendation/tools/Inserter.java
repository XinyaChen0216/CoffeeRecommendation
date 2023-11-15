package reviewApplication.tools;

import reviewApplication.dal.*;
import reviewApplication.model.*;
import reviewApplication.model.Restaurants.CuisineType;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * main() runner, used for the app demo.
 * <p>
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

    public static void main(String[] args) throws SQLException {

        // Create.
        UsersDao usersDao = UsersDao.getInstance();
        Users user = new Users("userName", "password", "firstname", "lastname", "firstName@yahoo.com", "0001112222");
        usersDao.create(user);
        
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        CreditCards creditCard = new CreditCards((long)12345678, new Date(20250901), user);
        creditCardsDao.create(creditCard);

        CompaniesDao companiesDao = CompaniesDao.getInstance();
        Companies company = new Companies("companyName", "about");
        companiesDao.create(company);
        
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        Restaurants restaurant = new Restaurants(1, "name", "description", "menu", "6am-6pm", true, CuisineType.AFRICAN, "streets1", "streets2", "city", "state", 98056, company);
        restaurantsDao.create(restaurant);

        SitDownRestaurantDao sitDownRestaurantDao = SitDownRestaurantDao.getInstance();
        SitDownRestaurant sitDownRestaurant = new SitDownRestaurant(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenu(), restaurant.getHours(), restaurant.isActive(), restaurant.getCuisineType(), restaurant.getStreet1(), restaurant.getStreet2(), restaurant.getCity(), restaurant.getState(), restaurant.getZip(), restaurant.getCompnay(), 8);
        sitDownRestaurantDao.create(sitDownRestaurant);

        TakeOutRestaurantDao takeOutRestaurantDao = TakeOutRestaurantDao.getInstance();
        TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenu(), restaurant.getHours(), restaurant.isActive(), restaurant.getCuisineType(), restaurant.getStreet1(), restaurant.getStreet2(), restaurant.getCity(), restaurant.getState(), restaurant.getZip(), restaurant.getCompnay(), 1);
        takeOutRestaurantDao.create(takeOutRestaurant);
 
        FoodCartRestaurantDao foodCartRestaurantsDao = FoodCartRestaurantDao.getInstance();
        FoodCartRestaurant foodCartRestaurant = new FoodCartRestaurant(restaurant.getRestaurantId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenu(), restaurant.getHours(), restaurant.isActive(), restaurant.getCuisineType(), restaurant.getStreet1(), restaurant.getStreet2(), restaurant.getCity(), restaurant.getState(), restaurant.getZip(), restaurant.getCompnay(), true);    
        foodCartRestaurantsDao.create(foodCartRestaurant);
        
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        Reviews review = new Reviews(1, Timestamp.valueOf("2020-11-05"), "nice food", 9.8, user, restaurant);
        reviewsDao.create(review);

        RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
        Recommendations recommendation = new Recommendations(1, user, restaurant);
        recommendationsDao.create(recommendation);
      
        ReservationsDao reservationsDao = ReservationsDao.getInstance();
        Reservations reservation = new Reservations(1, Timestamp.valueOf("2020-11-05"), Timestamp.valueOf("2022-10-01"), 5, user, restaurant);
        reservationsDao.create(reservation);

 
        // Read
        System.out.println(usersDao.getUserByUserName(user.getUserName()).toString());

        
        System.out.println(creditCardsDao.getCreditCardByCardNumber((long)123456).toString());
        for(CreditCards creditCard1 : creditCardsDao.getCreditCardsByUserName(user.getUserName())){
            System.out.println(creditCard1.toString());
        }
        
        
        System.out.println(companiesDao.getCompanyByCompanyName("SUNSHINECOMPANY").toString());

        
        System.out.println(restaurantsDao.getRestaurantById(restaurant.getRestaurantId()).toString());
        for(Restaurants restaurant1 : restaurantsDao.getRestaurantsByCompanyName(company.getCompanyName())){
            System.out.println(restaurant1.toString());
        }
        for(Restaurants restaurant2 : restaurantsDao.getRestaurantsByCuisine(Restaurants.CuisineType.AFRICAN)){
            System.out.println(restaurant2.toString());
        }

        
        System.out.println(sitDownRestaurantDao.getSitDownRestaurantById(restaurant.getRestaurantId()).toString());
        for(SitDownRestaurant restaurant3 : sitDownRestaurantDao.getSitDownRestaurantsByCompanyName(company.getCompanyName())){
            System.out.println(restaurant3.toString());
        }
        
        
        System.out.println(foodCartRestaurantsDao.getFoodCartRestaurantById(restaurant.getRestaurantId()).toString());
        for(FoodCartRestaurant restaurant4 : foodCartRestaurantsDao.getFoodCartRestaurantByCompanyName(company.getCompanyName())){
            System.out.println(restaurant4.toString());
        }
        
        
        System.out.println(takeOutRestaurantDao.getTakeOutRestaurantById(restaurant.getRestaurantId()).toString());
        for(TakeOutRestaurant restaurant5 : takeOutRestaurantDao.getTakeOutRestaurantByCompanyName(company.getCompanyName())){
            System.out.println(restaurant5.toString());
        }
        
        
        System.out.println(reviewsDao.getReviewById(review.getReviewId()).toString());
        for(Reviews review1 : reviewsDao.getReviewsByRestaurantId(restaurant.getRestaurantId())){
            System.out.println(review1.toString());
        }
        for(Reviews review2 : reviewsDao.getReviewsByUserName(user.getUserName())){
            System.out.println(review2.toString());
        }

        
        System.out.println(recommendationsDao.getRecommendationById(recommendation.getRecommendationId()).toString());
        for(Recommendations recommendation1 : recommendationsDao.getRecommendationsByUserName(user.getUserName())){
          System.out.println(recommendation1.toString());
        }
        for(Recommendations recommendation2 : recommendationsDao.getRecommendationsByRestaurantId(restaurant.getRestaurantId())){
          System.out.println(recommendation2.toString());
        }
        
        
        System.out.println(reservationsDao.getReservationById(reservation.getReservationId()).toString());
        for(Reservations reservation1: reservationsDao.getReservationsByUserName(restaurant.getName())){
            System.out.println(reservation1.toString());
        }
        for(Reservations reservation2: reservationsDao.getReservationsBySitDownRestaurantId(restaurant.getRestaurantId())){
            System.out.println(reservation2.toString());
        }

        
        // UPDATE
        System.out.println(creditCardsDao.updateExpiration(creditCard, new Date(20251010)).toString());
        System.out.println(companiesDao.updateAbout(company, "new about").toString());

        // DELETE.
        usersDao.delete(user);
        creditCardsDao.delete(creditCard);
        companiesDao.delete(company);
        restaurantsDao.delete(restaurant);
        sitDownRestaurantDao.delete(sitDownRestaurant);
        takeOutRestaurantDao.delete(takeOutRestaurant);
        foodCartRestaurantsDao.delete(foodCartRestaurant);
        reviewsDao.delete(review);
        recommendationsDao.delete(recommendation);   
        reservationsDao.delete(reservation);  
    }
}
