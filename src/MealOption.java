public class MealOption {
	
	private String meal;
	private String restaurantName;
	private double price;
	
	//MealOption constructor
	public MealOption(String meal, String restaurantName, double price) {
		this.meal = meal;
		this.restaurantName = restaurantName;
		this.price = price;
	}
	
	//MealOption constructor if no fields are passed to the method
	public MealOption() {
		this.meal = "None";
		this.restaurantName = "None";
		this.price = 0.00;
	}
	
	//Returns the name of the meal of the MealOption object
	public String getMeal() {
		return this.meal;
	}
	
	//Returns the name of the restaurant of the MealOption object
	public String getRestaurantName() {
		return this.restaurantName;
	}
	
	//Returns the price of the meal of the MealOption object
	public double getPrice() {
		return this.price;
	}
}
