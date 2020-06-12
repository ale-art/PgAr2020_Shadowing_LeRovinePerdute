package path.distance;

import city.City;
import city.Country;
import it.unibs.fp.mylib.MyMath;
import path.AbstractPathManager;

public class PathManagerDistance extends AbstractPathManager {
/**
 * <b>constructor </b>
 * @param cities
 */
	public PathManagerDistance(Country cities) {
		super(cities);
	}
/**
 * <b>Method</b> <br>
 * calculation the distance between two cities<br>
 * using a {@code City} coordinates <br>
 * 
 */
	@Override
	public double distance(City c1, City c2) {
		return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
	}

}
