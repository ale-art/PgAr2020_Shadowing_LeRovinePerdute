package usingGraphCalcPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import city.City;
import city.Country;


/**
 * This class calculated the best path go across A {@linkplain Country}<br>
 * 
 * @see DijkstraAlgorithm
 * @see Graph
 * @see Node
 */
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

	/**
	 * method that define how to calculate the distance between two City
	 * 
	 * @return the distance
	 */
	public abstract double distance(City c1, City c2);

	/**
	 * Constructor <br>
	 * create a {@linkplain Graph} with all {@linkplain Node} using the
	 * {@linkplain Country}'s{@linkplain City} information
	 * 
	 * @param cities
	 *            the {@linkplain Country} to use
	 * @param source
	 *            the City to start to calculate the {@linkplain DijkstraAlgorithm}
	 */
	protected AbstractPathManager(Country cities, City source) {

		Graph graph = new Graph();
		initialCountry = cities;
		Iterator<City> citiesItr = cities.getCities().iterator();
		while (citiesItr.hasNext()) {

			City thisCity = citiesItr.next();
			Node node = new Node(thisCity.getId());

			graph.addNode(node);
		}
		// System.out.println("Nodi copiati");
		Iterator<Node> nodeItr = graph.getGraph().iterator();

		// then added all the link
		// i have to do so, 'cause I can't create the link until i don't have all nodes
		while (nodeItr.hasNext()) {

			Node node = nodeItr.next();
			City thisCity = cities.getCity(node.getId());

			for (Integer id : thisCity.getLinkedCities()) {
				City linkedCity = cities.getCity(id);
				node.addDestination(graph.getSpecificNode(linkedCity.getId()), distance(thisCity, linkedCity));
			}

		}
		// at last create the best path for all the Nodes
		bestPath = DijkstraAlgorithm.calculateShortestPathFromSource(graph, graph.getSpecificNode(source.getId()));

	}

	/**
	 * Constructor <br>
	 * create a {@linkplain Graph} with all {@linkplain Node} using the
	 * {@linkplain Country}'s{@linkplain City} information
	 * 
	 * @param cities
	 *            the Collection of City to use
	 * @param source
	 *            the City to start to calculate the {@linkplain DijkstraAlgorithm}
	 */
	protected AbstractPathManager(Collection<City> cities, City source) {

		this(new Country(cities), source);
	}

	/**
	 * Constructor <br>
	 * create a {@linkplain Graph} with all {@linkplain Node} using the
	 * {@linkplain Country}'s{@linkplain City} information
	 * 
	 * @param cities
	 *            the Array of City to use
	 * @param source
	 *            the City to start to calculate the {@linkplain DijkstraAlgorithm}
	 */
	protected AbstractPathManager(City[] cities, City source) {

		this(new Country(cities), source);
	}

	/**
	 * @return the best Path arrive to {@code finish}, a {@linkplain Collection}of
	 *         City
	 * @param finish
	 *            the node/{@linkplain City} to arrive
	 */
	public Collection<City> getBestPath(City finish) {

		ArrayList<City> countryBestPath = new ArrayList<>();

		City lastCity = initialCountry.getCity(finish.getId());

		int id = lastCity.getId();

		Node searched = bestPath.getSpecificNode(id);

		List<Node> shortPath = searched.getShortestPath();

		for (Node node : shortPath) {
			countryBestPath.add(initialCountry.getCity(node.getId()));
		}
		countryBestPath.add(lastCity);

		return countryBestPath;
	}

	/**
	 * @return the costhPath
	 * @param finish
	 *            the cost path for that city
	 */
	public double costPath(City finish) {

		initialCountry.getCity(finish.getId());

		return bestPath.getSpecificNode(finish.getId()).getDistance();
	}
	// PROVE
	// public static void main(String args[]) throws FileNotFoundException,
	// XMLStreamException {
	//
	//// ArrayList<City> cities =
	// XmlManager.readCities(String.format("./input/PgAr_Map_%d.xml", 200));
	////
	//// long millS = System.nanoTime();
	//// City last=cities.get(cities.size()-1);
	//// AbstractPathManager path =new PathManagerDistance(new
	// Country(cities),cities.get(0));
	//// Collection<City> citta= path.getBestPath(last);
	////
	//// for (City city : citta) {
	//// System.out.print(city.getId()+"-> ");
	//// }
	//// System.out.println(path.costPath(last));
	////
	//// System.out.println("\n"+LocalTime.ofNanoOfDay( System.nanoTime()-millS ));
	////
	//// millS = System.nanoTime();
	////
	//// DijkstraCalculator dj = new DijkstraCalculator(cities);
	//// dj.calc(cities.get(0), cities.get(cities.size() - 1),
	// CostFunctionTypes.DISTANCE);
	////
	//// citta=dj.getPath();
	//// for (City city : citta) {
	//// System.out.print(city.getId()+"-> ");
	//// }
	//// System.out.println(dj.getTotalCost());
	//// System.out.println("\n"+LocalTime.ofNanoOfDay(System.nanoTime()-millS ));
	//
	//// Country c= new Country();
	//// ArrayList<Integer> in= new ArrayList<>();
	//// in.add(1);
	//// City c1= new City(0,2,3,4,"Hello");
	//// c1.addCitiesIds(in);
	//// c.add(c1);
	////
	//// c.add(new City(1,5,3,4,"Hello"));
	////
	//// AbstractPathManager pathManager = new PathManagerDistance(c);
	//// System.out.println(pathManager.getBestPath(c.getCity(1)));
	//// System.out.println(pathManager.getClass() == PathManagerDistance.class);
	//
	//
	// }
}
