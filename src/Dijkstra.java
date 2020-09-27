import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

//This implementation of Dijkstra's Algorithm is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class Dijkstra {
	
	private HashMap<MealStop, DirectedWeightedEdge> edgeTo;
	private HashMap<MealStop, Double> distTo;
	private HashMap<MealStop, Double> pq;
	
	//Dijkstra constructor
	public Dijkstra(RestaurantMap G, City c) {
		edgeTo = new HashMap<MealStop, DirectedWeightedEdge>(G.getStops());
		distTo = new HashMap<MealStop, Double>(G.getStops());
		pq = new HashMap<MealStop, Double>(G.getStops());
		
		//Creates a new temporary node with given City c
		MealStop m = new MealStop(new MealOption(), c);
		G.addFoodStop(m);
		
		//Initializes the HashMaps
		for (MealStop i : G.getFoodMap().keySet()) {
			//Connects new node to the other MealStop nodes in the same city with edge weight of 0
			if(i.getCity() == c && i.getMealOption() != m.getMealOption())
				G.addNextOptions(new DirectedWeightedEdge(m, i, 0.0));
			distTo.put(i, Double.POSITIVE_INFINITY);
			edgeTo.put(i, null);
		}
		distTo.replace(m, 0.0);
		pq.put(m, 0.0);

		while(!pq.isEmpty()) {
			//Finds the minimal MealStop object on the priority queue 
			double lowest = Double.POSITIVE_INFINITY;
			MealStop lowestIndex = null;
			for(MealStop i : pq.keySet()) {
				if(pq.get(i) < lowest) {
					lowest = pq.get(i);
					lowestIndex = i;
				}
			}
			
			pq.remove(lowestIndex);
			relax(G, lowestIndex);
		}
	}
	
	//Relaxes all the edges from a specified MealStop
	private void relax(RestaurantMap G, MealStop m) {
		for(DirectedWeightedEdge e : G.adj(m)) {
			MealStop w = e.getEnd();
			if(distTo.get(w) > distTo.get(m) + e.getWeight()) {
				distTo.replace(w, distTo.get(m) + e.getWeight());
				edgeTo.replace(w, e);
				if(pq.containsKey(w))
					pq.replace(w, distTo.get(w));
				else
					pq.put(w, distTo.get(w));
			}
		}
	}
	
	//Returns the distance to a MealStop object (cost to get to the specified MealStop)
	public double distTo(MealStop m) {
		return distTo.get(m);
	}
	
	//Returns whether there is a path to a particular vertex (City object)
	public boolean hasPathTo(MealStop m) {
		return distTo.get(m) < Double.POSITIVE_INFINITY;
	}
	
	//Returns the path from source vertex to the destination vertex by putting the City objects on a deque in reverse order
	public Iterable<DirectedWeightedEdge> pathTo(RestaurantMap G, City c) {
		//Determines if a path exists to the specified city
		for(MealStop m : G.getFoodMap().keySet())
			if(m.getCity() == c)
				if(!hasPathTo(m))
					return null;
		
		double cheapest = Double.POSITIVE_INFINITY;
		MealStop cheapestIndex = null;
		
		//Finds the cheapest route
		for(MealStop m : G.getFoodMap().keySet()) {
			if(m.getCity() == c && cheapest > distTo(m)) {
				cheapest = distTo(m);
				cheapestIndex = m;
			}
		}
		
		//Creates iterable path
		Deque<DirectedWeightedEdge> path = new ArrayDeque<DirectedWeightedEdge>();
		for(DirectedWeightedEdge i = edgeTo.get(cheapestIndex); i != null; i = edgeTo.get(i.getStart())) {
			path.push(i);
		}
		return path;
	}
	
	//Returns the shortest distance to a City (cost to get to the specified City)
	public double getDist(RestaurantMap G, City c) {
		//Determines if a path exists to the specified city
		for(MealStop m : G.getFoodMap().keySet())
			if(m.getCity() == c)
				if(!hasPathTo(m))
					return -1;
		
		double cheapest = Double.POSITIVE_INFINITY;
		
		//Finds the cheapest route's total cost
		for(MealStop m : G.getFoodMap().keySet()) {
			if(m.getCity() == c && cheapest > distTo(m)) {
				cheapest = distTo(m);
			}
		}
		return cheapest;
	}

}
