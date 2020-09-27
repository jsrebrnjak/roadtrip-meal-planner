/*Student Information
 *-------------------
 *Student Name: Srebrnjak, Justina
 *Student Number: 400189506
 *Course Code: SE 2XB3
 *Lab Section: 03
 *I attest that the following code being submitted is my own individual work.
 */
package cas.A2.wt;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<MealOption> allMeals;
	
	//Menu constructor
	public Menu() {
		allMeals = new ArrayList<MealOption>();
	}
	
	//Adds a MealOption object to the list of MealOption objects on the menu
	public void addMeal(MealOption v) {
		allMeals.add(v);
	}
	
	//Returns the cheapest MealOption object in regards to price from all MealOption objects in the Menu object
	public MealOption findCheapestOfAll() {
		MealOption cheapest = null;
		
		for(MealOption v : allMeals) {
			if(cheapest == null)
				cheapest = v;
			else
				if(cheapest.getPrice() > v.getPrice())
					cheapest = v;
		}
		return cheapest;
	}
	
	//Returns the second cheapest MealOption object in regards to price from all MealOption objects in the Menu object
	public MealOption find2ndCheapestOfAll() {
		MealOption cheapest2nd = null;
		
		for(MealOption v : allMeals) {
			if(cheapest2nd == null)
				cheapest2nd = v;
			else
				if(cheapest2nd.getPrice() > v.getPrice() && v != findCheapestOfAll())
					cheapest2nd = v;
		}
		return cheapest2nd;
	}
	
	//Returns the cheapest MealOption object in regards to price from MealOption objects in the Menu object and in the specified valid restaurants
	//If a restaurant is not in a city, the method can find the cheapest meal out of the restaurants available in that city
	public MealOption findCheapest(ArrayList<String> restaurants) {
		MealOption cheapest = null;
		boolean validRestaurant = false;
		
		for(MealOption v : allMeals) {
			
			//Checks if the MealOption is from a valid restaurant
			for(String name : restaurants) {
				if(v.getRestaurantName().equals(name)) {
					validRestaurant = true;
					break;
				}
			}
			
			if(validRestaurant) {
				if(cheapest == null)
					cheapest = v;
				else
					if(cheapest.getPrice() > v.getPrice())
						cheapest = v;
				validRestaurant = false;
			}
		}
		return cheapest;
	}
	
	//Returns the second cheapest MealOption object in regards to price from MealOption objects in the Menu object and in the specified valid restaurants
	//If a restaurant is not in a city, the method can find the second cheapest meal out of the restaurants available in that city
	public MealOption find2ndCheapest(ArrayList<String> restaurants) {
		MealOption cheapest2nd = null;
		boolean validRestaurant = false;
		
		for(MealOption v : allMeals) {
			
			//Checks if the MealOption is from a valid restaurant
			for(String name : restaurants) {
				if(v.getRestaurantName().equals(name)) {
					validRestaurant = true;
					break;
				}
			}
			
			if(validRestaurant) {
				if(cheapest2nd == null)
					cheapest2nd = v;
				else
					if(cheapest2nd.getPrice() > v.getPrice() && v != findCheapest(restaurants))
						cheapest2nd = v;
				validRestaurant = false;
			}
		}
		return cheapest2nd;
	}

}
