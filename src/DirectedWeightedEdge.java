/*Student Information
 *-------------------
 *Student Name: Srebrnjak, Justina
 *Student Number: 400189506
 *Course Code: SE 2XB3
 *Lab Section: 03
 *I attest that the following code being submitted is my own individual work.
 */
package cas.A2.wt;

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
