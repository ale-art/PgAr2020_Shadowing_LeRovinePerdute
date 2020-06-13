package usingArrayCalcPath;

import java.util.ArrayList;
import java.util.Collections;

import city.City;
import it.unibs.fp.mylib.MyMath;

/**
 * <b>Class</B> to calculate the Dijkstra algorithm
 */
public class DijkstraCalculator {
	private ArrayList<City> cities;
	private ArrayList<City> path;
	private double totalCost;

	/**
	 * <b>Constructor</B> of the calculator
	 * 
	 * @param cities
	 *            the graph on which execute the algorithm
	 */
	public DijkstraCalculator(ArrayList<City> cities) {
		this.cities = cities;
		this.path = new ArrayList<>();
		totalCost = 0;
	}

	/**
	 * <b>Constructor</B> of the calculator
	 */
	public DijkstraCalculator() {
		this.cities = new ArrayList<>();
		this.path = new ArrayList<>();
		totalCost = 0;
	}

	/**
	 * <b>Method</B> <br>
	 * Given the cities which are not "calculated" yet, find the one that has the
	 * least distance from the origin
	 * 
	 * @param distances
	 *            the distances of all the cities from the origin
	 * @param remainderCities
	 *            the city which are not "calculated" yet
	 * @return the {@code City} with the minimum distance
	 */
	private int getMinId(double[] distances, ArrayList<City> remainderCities) {
		// Set as minimum the first city of the cities not calculated
		double min = distances[remainderCities.get(0).getId()];
		int minId = remainderCities.get(0).getId();

		// For each city find the one with the least distance
		for (int i = 1; i < remainderCities.size(); i++) {
			int id = remainderCities.get(i).getId();

			if (distances[id] < min) {
				min = distances[id];
				minId = id;
			}
		}

		return minId;
	}

	/**
	 * <b>Method</B> <br>
	 * Find a city from the"graph" with the specified id
	 * 
	 * @param id
	 *            the id of the city to find
	 * @return the city with the specified id
	 */
	private City getCityById(int id) {
		return this.cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
	}

	/**
	 * The cost function for the first team (Euclidean distance between two points)
	 * 
	 * @param c1
	 *            the first city
	 * @param c2
	 *            the second city
	 * @return the Euclidean distance between the specified cities
	 */
	private double getDistance(City c1, City c2) {
		return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
	}

	/**
	 * The cost function for the second team (length of an unidimensional segment)
	 * 
	 * @param c1
	 *            the first city
	 * @param c2
	 *            the second city
	 * @return the difference between the height of the specified cities
	 */
	private double getHeightDifference(City c1, City c2) {
		return Math.abs(c1.getH() - c2.getH());
	}

	/**
	 * Calculate the Dijkstra algorithm
	 * 
	 * @param source
	 *            the source from which start the calculations
	 * @param destination
	 *            the destination to reach
	 * @param type
	 *            the type of the cost function to use
	 */
	public void calc(City source, City destination, CostFunctionTypes type) {
		// Create data structure to save the data for the algorithm
		ArrayList<City> newList = new ArrayList<>();
		double[] distances = new double[this.cities.size()];
		int[] previousId = new int[this.cities.size()];

		/**
		 * For each city of the graph set its distance from the source to infinity and
		 * the id of the previous city to null (-1)
		 */
		for (City city : this.cities) {
			int id = city.getId();

			distances[id] = Double.POSITIVE_INFINITY;
			previousId[id] = -1;

			newList.add(city);
		}

		// The distance to reach the source from the source must be 0
		distances[source.getId()] = 0;

		// For each city calculate its best distance from the source
		while (!newList.isEmpty()) {
			// Always start selecting the city whit best distance (lowest cost)
			City c = getCityById(getMinId(distances, newList));
			newList.remove(c);

			// Calculate the distance for each neighbour
			for (int ids : c.getLinkedCities()) {
				City neighbour = getCityById(ids);

				// If a neighbour is already being calculated, go ahead
				if (!newList.contains(neighbour)) {
					continue;
				}

				// Get the distance based on the specified cost function
				double distance = type == CostFunctionTypes.HEIGHT ? getHeightDifference(c, neighbour)
						: getDistance(c, neighbour);

				double calcDist = distances[c.getId()] + distance;

				// If the new distance is better (lower), save it and delete the precedent
				if (calcDist < distances[neighbour.getId()]) {
					distances[neighbour.getId()] = calcDist;
					previousId[neighbour.getId()] = c.getId();
				}
			}
		}

		ArrayList<City> path = new ArrayList<>();

		// Create an ArrayList of cities which represent the best path
		int startId = destination.getId();
		while (previousId[startId] != -1) {
			path.add(getCityById(startId));
			startId = previousId[startId];
		}

		path.add(getCityById(source.getId()));
		Collections.reverse(path);

		// Save the information about the best path and the total cost
		this.path = path;
		this.totalCost = distances[destination.getId()];
	}

	public ArrayList<City> getPath() {
		return this.path;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
}