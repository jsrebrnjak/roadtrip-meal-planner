/*Student Information
 *-------------------
 *Student Name: Srebrnjak, Justina
 *Student Number: 400189506
 *Course Code: SE 2XB3
 *Lab Section: 03
 *I attest that the following code being submitted is my own individual work.
 */
package cas.A2.wt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		FileWriter output;
		Map map = new Map();
		
		BufferedReader inputCities = new BufferedReader(new FileReader("2XB3_A2_DataSets/USCities.csv"));
		
		//Reads through the USCities file and creates City objects that then become vertices in the map
		inputCities.readLine();
		String current;
		while((current = inputCities.readLine()) != null) {
			String[] s = current.split(",");
			City toAdd = new City(s[3], Double.parseDouble(s[4]) , Double.parseDouble(s[5]));
			map.addCity(toAdd);
		}
		inputCities.close();
		
		BufferedReader inputRoads = new BufferedReader(new FileReader("2XB3_A2_DataSets/connectedCities.txt"));
		
		//Reads through the connectedCities file and creates edges in the map
		while((current = inputRoads.readLine()) != null) {
			String[] s = current.split(",");
			s[1] = s[1].substring(1);
				
			City startCity = null;
			City endCity = null;
				
			for (City i : map.getMap().keySet()) {
				if(i.getName().equalsIgnoreCase(s[0]))
					startCity = i;
				else if(i.getName().equalsIgnoreCase(s[1]))
					endCity = i;
			}
			map.addRoad(startCity, endCity);
		}
		inputRoads.close();
		
		BufferedReader inputMenu = new BufferedReader(new FileReader("2XB3_A2_DataSets/menu.csv"));
		inputMenu.readLine();
		Menu menu = new Menu();
		
		//Reads through the menu file and creates MealOption objects that are added to the Menu object
		while((current = inputMenu.readLine()) != null) {
			String[] s = current.split(",");
			s[2] = s[2].substring(1);
			
			if(s[0].equals("McDonald’s"))
				s[0] = s[0].substring(0, 8) + "'s";
			else if (s[0].equals("Wendy’s"))
				s[0] = s[0].substring(0, 5) + "'s";
			
			menu.addMeal(new MealOption(s[1], s[0], Double.parseDouble(s[2])));
		}
		inputMenu.close();
		
		//Finds the cheapest meals out of all the MealOptions in the Menu object
		MealOption cheapestMeal = menu.findCheapestOfAll();
		MealOption cheapest2ndMeal = menu.find2ndCheapestOfAll();
		
		BufferedReader inputMc = new BufferedReader(new FileReader("2XB3_A2_DataSets/mcdonalds.csv"));
		inputMc.readLine();
		
		RestaurantMap foodMap = new RestaurantMap();
		
		//Reads the mcdonalds file and determines if there is a McDonald's located in every City in the map
		while((current = inputMc.readLine()) != null) {
			String[] s = current.split(",");
			for(City i : map.getMap().keySet()) {
				if(Math.abs(Double.parseDouble(s[1]) - i.getLat()) <= 0.5 && Math.abs(Double.parseDouble(s[0]) - i.getLon()) <= 0.5)
					i.setContainsMc(true);
			}
		}
		inputMc.close();
		
		BufferedReader inputBK = new BufferedReader(new FileReader("2XB3_A2_DataSets/burgerking.csv"));
		inputBK.readLine();
		
		//Reads the burgerking file and determines if there is a Burger King located in every City in the map
		while((current = inputBK.readLine()) != null) {
			String[] s = current.split(",");
			for(City i : map.getMap().keySet()) {
				if(Math.abs(Double.parseDouble(s[1]) - i.getLat()) >= 0.5 && Math.abs(Double.parseDouble(s[0]) - i.getLon()) >= 0.5)
					i.setContainsBK(true);
			}
		}
		inputBK.close();
		
		BufferedReader inputWend = new BufferedReader(new FileReader("2XB3_A2_DataSets/wendys.csv"));
		inputWend.readLine();
		
		//Reads the wendys file and determines if there is a Wendy's located in every City in the map
		while((current = inputWend.readLine()) != null) {
			String[] s = current.split(",");
			for(City i : map.getMap().keySet()) {
				if(Math.abs(Double.parseDouble(s[1]) - i.getLat()) >= 0.5 && Math.abs(Double.parseDouble(s[0]) - i.getLon()) >= 0.5)
					i.setContainsWend(true);
			}
		}
		inputWend.close();
		
		//Creates MealStop objects including the cheapest MealOption objects in each City (based on what restaurants are present in each City) 
		//and the City itself
		//The MealStop objects are added to foodMap
		for(City i : map.getMap().keySet()) {
			if(i.getContainsMc() && i.getContainsBK() && i.getContainsWend()) { 
				foodMap.addFoodStop(new MealStop(cheapestMeal, i));
				foodMap.addFoodStop(new MealStop(cheapest2ndMeal, i));
			} else {	
				if(!i.getContainsMc() && i.getContainsBK() && i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("Burger King");
					availableRestaurants.add("Wendy's");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				} else if(i.getContainsMc() && !i.getContainsBK() && i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("McDonald's");
					availableRestaurants.add("Wendy's");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				} else if(i.getContainsMc() && i.getContainsBK() && !i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("McDonald's");
					availableRestaurants.add("Burger King");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				} else if(!i.getContainsMc() && !i.getContainsBK() && i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("Wendy's");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				} else if(!i.getContainsMc() && i.getContainsBK() && !i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("Burger King");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				} else if(i.getContainsMc() && !i.getContainsBK() && !i.getContainsWend()) {
					ArrayList<String> availableRestaurants = new ArrayList<String>();
					availableRestaurants.add("McDonald's");
					foodMap.addFoodStop(new MealStop(menu.findCheapest(availableRestaurants), i));
					foodMap.addFoodStop(new MealStop(menu.find2ndCheapest(availableRestaurants), i));
					
				}
			}
		}
		
		//DirectedWeightedEdges are created and added to the foodMap
		for(MealStop i : foodMap.getFoodMap().keySet()) {
			for(City j : i.getCity().goesTo()) {
				for(MealStop k : foodMap.getFoodMap().keySet()) {
					if(k.getCity() == j)
						if(i.getMealOption() != k.getMealOption())
							foodMap.addNextOptions(new DirectedWeightedEdge(i, k, k.getMealOption().getPrice()));
				}
			}
		}
		
		//Writing to Output File begins
		output = new FileWriter("a2_out.txt");
		
		//Writes BFS path to the output file
		output.write("BFS: ");
		BFS pathBFS = new BFS(map, map.findCity("Boston"));
		boolean first = true;
		Iterator<City> itr = pathBFS.pathTo(map.findCity("Minneapolis")).iterator();
		while(itr.hasNext()) {
			if(first) {
				output.write(itr.next().getName());
				first = false;
			} else {
				output.write(", " + itr.next().getName());
			}
		}
		output.write("\n");
		
		//Writes DFS path to the output file
		output.write("DFS: ");
		DFS pathDFS = new DFS(map, map.findCity("Boston"));
		first = true;
		itr = pathDFS.pathTo(map.findCity("Minneapolis")).iterator();
		while(itr.hasNext()) {
			if(first) {
				output.write(itr.next().getName());
				first = false;
			} else {
				output.write(", " + itr.next().getName());
			}
		}
		output.write("\n\n");
		
		//Creates a table of the shortest path and displays the name of the city, the meal, the restaurant, and the cost of the meal
		output.write(String.format("%-20s %-30s %-20s %-20s \r\n", "City", "Meal Choice", "Cost of Meal", "Restaurant"));
		
		Dijkstra dijk = new Dijkstra(foodMap, map.findCity("Boston"));

		DirectedWeightedEdge end = null;
		first = true;
		boolean second = false;
		for(DirectedWeightedEdge i : dijk.pathTo(foodMap, map.findCity("Minneapolis"))) {
			if(first && !second) {
				output.write(String.format("%-20s %-30s %-20s %-20s \r\n", i.getStart().getCity().getName(), i.getStart().getMealOption().getMeal(), "$" + i.getStart().getMealOption().getPrice(), i.getStart().getMealOption().getRestaurantName()));
				first = false;
				second = true;
			} else if (!first && second) {
				second = false;
			} else {
				output.write(String.format("%-20s %-30s %-20s %-20s \r\n", i.getStart().getCity().getName(), i.getStart().getMealOption().getMeal(), "$" + i.getStart().getMealOption().getPrice(), i.getStart().getMealOption().getRestaurantName()));
				end = i;
			}
		}
		output.write(String.format("%-20s %-30s %-20s %-20s \r\n", end.getEnd().getCity().getName(), end.getEnd().getMealOption().getMeal(), "$" + end.getEnd().getMealOption().getPrice(), end.getEnd().getMealOption().getRestaurantName()));
		output.write("The total cost of this trip is $" + dijk.getDist(foodMap, map.findCity("Minneapolis")));
		output.close();
	}

}
