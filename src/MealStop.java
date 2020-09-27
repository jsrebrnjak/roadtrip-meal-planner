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

public class MealStop {
	
	private MealOption mealOption;
	private City city;
	private ArrayList<DirectedWeightedEdge> goesTo;
	
	//MealStop constructor
	public MealStop(MealOption meal, City city) {
		this.mealOption = meal;
		this.city = city;
		goesTo = new ArrayList<DirectedWeightedEdge>();
	}
	
	//Returns the MealOption object of the MealStop object
	public MealOption getMealOption() {
		return this.mealOption;
	}
	
	//Returns the City object of the MealStop object
	public City getCity() {
		return this.city;
	}
	
	//Adds a DirectedWeightedEdge to the list of other MealStop objects that the current MealStop object goes to
	public void addNextStop(DirectedWeightedEdge edge) {
		goesTo.add(edge);
	}
	
	//Returns the list of DirectedWeightedEdge objects consisting of the other MealStop objects that the current MealStop object goes to
	public ArrayList<DirectedWeightedEdge> goesTo() {
		return this.goesTo;
	}

}
