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

//This implementation of DFS is similar to that found in Robert Sedgewick and Kevin Wayne's "Algorithm's Fourth Edition"
public class DFS {
	
	private HashMap<City, Boolean> marked;
	private HashMap<City, City> edgeTo;
	private final City s;

	//DFS constructor
	public DFS(Map map, City city) {
		marked = new HashMap<City, Boolean>();
		edgeTo = new HashMap<City, City>();
		
		for (City i : map.getMap().keySet()) {
			  marked.put(i, false);
			  edgeTo.put(i, null);
		}
		
		this.s = city;
		DFSUtil(map, city);
	}
	
	//DFS Algorithm implemented using recursion to discover all nodes
	private void DFSUtil(Map m, City v) {
		marked.replace(v, true);
		for (City w : m.adj(v)) {
			if(!marked.get(w)) {
				edgeTo.replace(w, v);
				DFSUtil(m, w);
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
