package usingGraphCalcPath.height;

import java.util.Collection;

import city.City;
import city.Country;
import usingGraphCalcPath.AbstractPathManager;
import usingGraphCalcPath.DijkstraAlgorithm;
import usingGraphCalcPath.Graph;
import usingGraphCalcPath.Node;

/**
 * Calculate the best <b>{@code Path}</b> using the
 * {@linkplain DijkstraAlgorithm}
 */
public class PathManagerHeight extends AbstractPathManager {
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
	public PathManagerHeight(Country cities, City source) {
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
	public PathManagerHeight(Collection<City> cities, City source) {
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
	public PathManagerHeight(Country cities) {
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
	public PathManagerHeight(Collection<City> cities) {
		this(new Country(cities));
	}

	/**
	 * <b>Method</B> <br>
	 * returning the difference between two {@link City} height <br>
	 */
	@Override
	public double distance(City c1, City c2) {
		return Math.abs(c1.getH() - c2.getH());
	}

}
