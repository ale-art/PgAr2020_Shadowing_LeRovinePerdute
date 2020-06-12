package usingGraphCalcPath.distance;

import java.util.Collection;

import city.City;
import city.Country;
import it.unibs.fp.mylib.MyMath;
import usingGraphCalcPath.AbstractPathManager;
import usingGraphCalcPath.DijkstraAlgorithm;
import usingGraphCalcPath.Graph;
import usingGraphCalcPath.Node;

public class PathManagerDistance extends AbstractPathManager {

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
	public PathManagerDistance(Country cities, City source) {
		super(cities, source);
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
	public PathManagerDistance(Collection<City> cities, City source) {
		super(cities, source);
	}

	/**
	 * Constructor <br>
	 * create a {@linkplain Graph} with all {@linkplain Node} using the
	 * {@linkplain Country}'s{@linkplain City} information, the source City is the
	 * city with id= 0
	 * 
	 * @param cities
	 *            the {@linkplain Country} to use
	 */
	public PathManagerDistance(Country cities) {
		super(cities, cities.getCity(0));
	}

	/**
	 * Constructor <br>
	 * create a {@linkplain Graph} with all {@linkplain Node} using the
	 * {@linkplain Country}'s{@linkplain City} information, the source City is the
	 * city with id= 0
	 * 
	 * @param cities
	 *            the Collection of City to use
	 */
	public PathManagerDistance(Collection<City> cities) {
		this(new Country(cities));
	}

	/**
	 * <b>Method</B> <br>
	 * calculation the distance between two cities<br>
	 * using a {@code City} coordinates <br>
	 * the MyMath class provide this method in order to simplify the code structure
	 * <br>
	 * 
	 */
	@Override
	public double distance(City c1, City c2) {
		return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
	}

}
