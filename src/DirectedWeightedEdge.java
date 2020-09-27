//This implementation of a directed and weighted edge is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class DirectedWeightedEdge {
	
	private MealStop start;
	private MealStop end;
	private double weight;
	
	//DirectedWeightedEdge constructor
	public DirectedWeightedEdge(MealStop start, MealStop end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	//Returns the MealStop object that the edge goes from
	public MealStop getStart() {
		return this.start;
	}
	
	//Returns the MealStop object that the edge goes to
	public MealStop getEnd() {
		return this.end;
	}
	
	//Returns the weight of the DirectedWeightedEdge object
	public double getWeight() {
		return this.weight;
	}

}
