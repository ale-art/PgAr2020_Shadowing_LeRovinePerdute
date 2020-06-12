package slowCalcPath;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import bestCalcPath.CostFunctionTypes;
import bestCalcPath.DijkstraCalculator;
import city.City;
import utils.XmlManager;
@Deprecated
/**This class calculated the best path from the first City with id=0<br>
 * to the City with the most high id<br>
 * But is too slow, don't use this. <br>
 * Use {@link DijkstraCalculator}*/
public abstract class AbstractPathManager {

	/**
	 * <b>Attribute</b> <br>
	 * provide the best path in the {@linkplain Graph} given <br>
	 */
	private Graph bestPath;
	
	/**
	 * <b>Attribute</b> <br>
	 * provide the initial {@link Country} <br>
	 * 
	 * 
	 */
	private Country initialCountry;

	public abstract double distance(City c1, City c2);

	protected AbstractPathManager(Country cities) {

		Graph graph = new Graph();
		initialCountry = cities;
		Iterator<City> citiesItr=cities.getCities().iterator();
		while (citiesItr.hasNext()) {

			City thisCity = citiesItr.next();
			Node node = new Node(thisCity.getId());

			graph.addNode(node);
		}
		System.out.println("Nodi copiati");
		Iterator<Node> nodeItr = graph.getGraph().iterator();
		// then added all the link
		// i have to do so, 'cause I can't create the link until i don't have all nodes
		while (nodeItr.hasNext()) {

			Node node = nodeItr.next();
			City thisCity = cities.getCity(node.getId());// lento

			for (Integer id : thisCity.getLinkedCities()) {
				City linkedCity = cities.getCity(id);
				node.addDestination(graph.getSpecificNode(linkedCity.getId())/* questa parte e' molta lenta */,
						distance(thisCity, linkedCity));

			}
		}
		System.out.println("Link copiati");// at last create the best path for all the Nodes
		bestPath = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getSpecificNode(0));
		System.out.println("Miglior percorso creato");
	}

	protected AbstractPathManager(Collection<City> cities) {

		this(new Country(cities));
	}

	protected AbstractPathManager(City[] cities) {

		this(new Country(cities));
	}

	public Collection<City> getBestPath() {

		Country countryBestPath = new Country();

		City lastCity = initialCountry.getCity(initialCountry.size() - 1);

		int id = lastCity.getId();

		Node searched = bestPath.getSpecificNode(id);

		List<Node> shortPath = searched.getShortestPath();

		for (Node node : shortPath) {
			countryBestPath.add(initialCountry.getCity(node.getId()));
		}

		return countryBestPath.getCities();
	}
	
	
}
