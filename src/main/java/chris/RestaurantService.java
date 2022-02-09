package chris;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    // customer
    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        for (Restaurant restaurant : restaurants) {// loop through each restaurant in the restaurants list
            if(restaurant.getName().equals(restaurantName)){// fetches the restaurant name and compares with parameter
                return restaurant;// returns the matching restaurant object and finishes the loop
            }
        }
        throw new restaurantNotFoundException("Restaurant " + restaurantName + " not found");
    }

    // admin
    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }
    //admin
    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }
    // customer
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

}
