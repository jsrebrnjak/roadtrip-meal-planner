import java.util.ArrayList;
import java.util.HashMap;

//This implementation of a edge weighted digraph is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class RestaurantMap {
	
	private int stops;
	private int nextOptions;
	private HashMap<MealStop, ArrayList<DirectedWeightedEdge>> foodMap;
	
	//RestaurantMap constructor that represents an Edge Weighted Digraph
	public RestaurantMap() {
		this.stops = 0;
		this.nextOptions = 0;
		foodMap = new HashMap<MealStop, ArrayList<DirectedWeightedEdge>>();
	}
	
	//Adds a MealStop object to the HashMap that represents the map as an adjacency list
	public void addFoodStop(MealStop m) {
		foodMap.put(m, m.goesTo());
		stops++;
	}
	
	//Adds a DirectedWeightedEdge object to the HashMap that represents the map as an adjacency list
	public void addNextOptions(DirectedWeightedEdge edge) {
		foodMap.get(edge.getStart()).add(edge);
		nextOptions++;
	}
	
	//Returns the number of vertices in the graph (MealStop objects in the map)
	public int getStops() {
		return this.stops;
	}
	
	//Returns the number of edges in the graph (DirectedWeightedEdge objects in the map)
	public int getNextOptions() {
		return this.nextOptions;
	}
	
	//Returns the HashMap representing the map as an adjacency list
	public HashMap<MealStop, ArrayList<DirectedWeightedEdge>> getFoodMap() {
		return foodMap;
	}

	//Returns a list of all the DirectedWeightedEdge objects that describe the MealStop objects that can be reached from the input MealStop
	public ArrayList<DirectedWeightedEdge> adj(MealStop m) {
		return foodMap.get(m);
	}

}
