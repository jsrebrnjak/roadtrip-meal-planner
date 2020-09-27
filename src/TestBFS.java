import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBFS {
	
	private static Map map;
	private static BFS bfs1;
	private static BFS bfs2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		map = new Map();
		
		//Reads through the USCities file and creates City objects that then become vertices in the map
		BufferedReader inputCities = new BufferedReader(new FileReader("2XB3_A2_DataSets/USCities.csv"));
		inputCities.readLine();
		String current;
		while((current = inputCities.readLine()) != null) {
			String[] s = current.split(",");
			City toAdd = new City(s[3], Double.parseDouble(s[4]) , Double.parseDouble(s[5]));
			map.addCity(toAdd);
		}
		inputCities.close();
		
		//Reads through the connectedCities file and creates edges in the map
		BufferedReader inputRoads = new BufferedReader(new FileReader("2XB3_A2_DataSets/connectedCities.txt"));
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
		
		//Creates BFS implementations from source City objects
		bfs1 = new BFS(map, map.findCity("San Antonio"));
		bfs2 = new BFS(map, map.findCity("Los Angeles"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		map = null;
		bfs1 = null;
		bfs2 = null;
	}

	@Test
	public void testHasPathTo() {
		//Tests that there is no path from Los Angeles to Boston since there are no roads leading to Boston
		assertFalse(bfs2.hasPathTo(map.findCity("Boston")));
		
		//Tests that there is a valid path from Los Angeles to Jacksonville
		assertTrue(bfs2.hasPathTo(map.findCity("Jacksonville")));
		
		//Tests that there is no path from San Antonio to St.Louis since San Antonio has no out going edges
		assertFalse(bfs1.hasPathTo(map.findCity("St. Louis")));
		
		//Tests that there is a path from San Antonio to itself even though though it has no out going edges
		assertTrue(bfs1.hasPathTo(map.findCity("San Antonio")));
	}

	@Test
	public void testPathTo() {
		//Constructs the expected path from Los Angeles to Jacksonville
		ArrayList<City> route1 = new ArrayList<City>();
		route1.add(map.findCity("Los Angeles"));
		route1.add(map.findCity("Phoenix"));
		route1.add(map.findCity("Albuquerque"));
		route1.add(map.findCity("Dallas"));
		route1.add(map.findCity("Houston"));
		route1.add(map.findCity("New Orleans"));
		route1.add(map.findCity("Jacksonville"));
		
		
		//Constructs the expected path from San Antonio to itself
		ArrayList<City> route2 = new ArrayList<City>();
		route2.add(map.findCity("San Antonio"));
		
		//Constructs the expected path from Los Angeles to Seattle
		ArrayList<City> route3 = new ArrayList<City>();
		route3.add(map.findCity("Los Angeles"));
		route3.add(map.findCity("San Francisco"));
		route3.add(map.findCity("Portland"));
		route3.add(map.findCity("Seattle"));
		
		//Tests the path from Los Angeles to Jacksonville with the correct cities and that they are in the correct order
		int index = 0;
		boolean samePath = true;
		for(City i : bfs2.pathTo(map.findCity("Jacksonville"))) {
			if(i != route1.get(index)) {
				samePath = false;
				break;
			}
			index++;
			
		}
		assertTrue(samePath);
		
		//Tests the path from San Antonio to itself (only contains itself)
		index = 0;
		samePath = true;
		for(City i : bfs1.pathTo(map.findCity("San Antonio"))) {
			if(i != route2.get(index)) {
				samePath = false;
				break;
			}
			index++;
		}
		assertTrue(samePath);
		
		//Tests the path from Los Angeles to Seattle with the correct cities and that they are in the correct order
		index = 0;
		samePath = true;
		for(City i : bfs2.pathTo(map.findCity("Seattle"))) {
			if(i != route3.get(index)) {
				samePath = false;
				break;
			}
			index++;
		}
		assertTrue(samePath);
		
		//Tests that null is returned when there is no available path
		Iterable<City> routeNull = bfs2.pathTo(map.findCity("Boston"));
		assertTrue(routeNull == null);
	}

}
