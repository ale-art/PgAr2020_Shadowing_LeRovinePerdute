package path;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import city.City;
import city.Country;

public abstract class AbstractPathManager {

	private Graph bestPath;
	
	private Country initialCountry;
	
	public abstract double distance(City c1, City c2);

	protected AbstractPathManager(Country cities) {
		
		Graph graph=new Graph();
		initialCountry=cities;
		Iterator<City> cties=cities.iterator();
		
		while(cties.hasNext()) {
			
			City thisCity=cties.next();
			Node node= new Node(thisCity.getId());
			
			for (Integer id: thisCity.getLinkedCities()) {
				City linkedCity= cities.getCity(id);
				node.addDestination(node, distance(thisCity,linkedCity));
			}
			
			graph.addNode(node);
		}
		bestPath=DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getRoot());
	}

	protected AbstractPathManager(Collection<City> cities) {

		this(new Country(cities));
	}

	protected AbstractPathManager(City[] cities) {

		this(new Country(cities));
	}

	public Collection<City> getBestPath(){
		
		Country countryBestPath=new Country();
		
		City lastCity=initialCountry.getCity(initialCountry.size()-1);
		
		int id=lastCity.getId();
		
		Node searched= bestPath.getSpecificNode(id);
		
		List<Node> shortPath=searched.getShortestPath();
	
		for (Node node : shortPath) {
			countryBestPath.add(initialCountry.getCity(node.getId()));
		}
		
		return countryBestPath.getCities();
	}
}
