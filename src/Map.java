import java.util.ArrayList;
import java.util.HashMap;

//This implementation of a digraph is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class Map {
	
	private int cities;
	private int roads;
	private HashMap<City, ArrayList<City>> map;
	
	//RestaurantMap constructor that represents a Digraph
	public Map() {
		this.cities = 0;
		this.roads = 0;
		map = new HashMap<City, ArrayList<City>>();
	}
	
	//Adds a City object to the HashMap that represents the map as an adjacency list
	public void addCity(City name) {
		map.put(name, name.goesTo());
		cities++;
	}
	
	//Adds a edge to the HashMap that represents the map as an adjacency list by adding a City object to the start city adjacency list
	public void addRoad(City start, City end) {
		map.get(start).add(end);
		roads++;
	}
	
	//Returns the number of vertices in the graph (City objects in the map)
	public int getCities() {
		return this.cities;
	}
	
	//Returns the number of edges in the graph (unidirectional roads in the map)
	public int getRoads() {
		return this.roads;
	}
	
	//Returns the HashMap representing the map as an adjacency list
	public HashMap<City, ArrayList<City>> getMap() {
		return map;
	}
	
	//Returns a City object that can be found by comparing the input city name and finding which City object's name matches
	public City findCity(String name) {
		for (City i : map.keySet()) {
			if(i.getName().equalsIgnoreCase(name))
				return i;
		}
		return null;
	}
	
	//Returns a list of all the City objects that can be reached from the input City
	public ArrayList<City> adj(City v) {
		return map.get(v);
	}
}
