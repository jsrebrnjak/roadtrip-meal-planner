/*Student Information
 *-------------------
 *Student Name: Srebrnjak, Justina
 *Student Number: 400189506
 *Course Code: SE 2XB3
 *Lab Section: 03
 *I attest that the following code being submitted is my own individual work.
 */
package cas.A2.wt;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//This implementation of BFS is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class BFS {
	
	private HashMap<City, Boolean> marked;
	private HashMap<City, City> edgeTo;
	private final City s;
	
	//BFS constructor
	public BFS(Map map, City city) {
		marked = new HashMap<City, Boolean>();
		edgeTo = new HashMap<City, City>();
		
		for (City i : map.getMap().keySet()) {
			  marked.put(i, false);
			  edgeTo.put(i, null);
		}
		
		this.s = city;
		BFSUtil(map, city);
	}
	
	//BFS Algorithm using a queue to discover all nodes in the shortest path
	private void BFSUtil(Map m, City v) {
		Queue<City> queue = new LinkedList<City>();
		marked.replace(v, true);
		queue.add(v);
		while(!queue.isEmpty()) {
			City w = queue.remove();
			for (City x : m.adj(w)) {
				if(!marked.get(x)) {
					edgeTo.replace(x, w);
					marked.replace(x, true);
					queue.add(x);
				}
			}
		}
	}
	
	//Returns whether there is a path to a particular vertex (City object)
	public boolean hasPathTo(City v) {
		return marked.get(v);
	}
	
	//Returns the path from source vertex to the destination vertex by putting the City objects on a deque in reverse order
	public Iterable<City> pathTo(City city) {
		if(!hasPathTo(city))
			return null;
		Deque<City> path = new ArrayDeque<City>();
		for(City i = city; i != s; i = edgeTo.get(i)) {
			path.push(i);
		}
		path.push(s);
		return path;
	}

}
