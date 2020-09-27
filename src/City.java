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

public class City {

	private String name;
	private double lat;
	private double lon;
	private ArrayList<City> goesTo;
	private boolean containsMc;
	private boolean containsBK;
	private boolean containsWend;
	
	//City constructor
	public City(String name, double lat, double lon) {
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		goesTo = new ArrayList<City>();
		this.containsMc = false;
		this.containsBK = false;
		this.containsWend = false;
	}
	
	//Returns the name of the City object
	public String getName() {
		return this.name;
	}
	
	//Returns the latitude of the City object
	public double getLat() {
		return this.lat;
	}
	
	//Returns the Longitude of the City object
	public double getLon() {
		return this.lon;
	}
	
	//Returns the list of City objects that the City object current can go to
	public ArrayList<City> goesTo() {
		return goesTo;
	}
	
	//Returns true if there is a specified restaurant located in the City object by comparing longitude and latitude; false otherwise
	//The absolute value of the restaurant's latitude/longitude subtracted from City object's latitude/longitude must be less than or equal to
	//0.5 to be considered inside the city
	public boolean containsRestaurant(double latitude, double longitude) {
		if(Math.abs(getLat() - latitude) <= 0.5 && Math.abs(getLon() - longitude) <= 0.5)
			return true;
		return false;			
	}
	
	//Returns true if there is a McDonald's in the City object; false otherwise
	public boolean getContainsMc() {
		return this.containsMc;
	}
	
	//Sets the value of containsMc to true or false
	public void setContainsMc(boolean value) {
		this.containsMc = value;
	}
	
	//Returns true if there is a Burger King in the City object; false otherwise
	public boolean getContainsBK() {
		return this.containsBK;
	}
	
	//Sets the value of containsBK to true or false
	public void setContainsBK(boolean value) {
		this.containsBK = value;
	}
	
	//Returns true if there is a Wendy's in the City object; false otherwise
	public boolean getContainsWend() {
		return this.containsWend;
	}
	
	//Sets the value of containsWend to true or false
	public void setContainsWend(boolean value) {
		this.containsWend = value;
	}
}
