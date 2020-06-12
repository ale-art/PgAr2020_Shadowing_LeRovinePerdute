package slowCalcPath.distance;

import city.City;
import it.unibs.fp.mylib.MyMath;
import slowCalcPath.AbstractPathManager;
import slowCalcPath.Country;

public class PathManagerDistance extends AbstractPathManager {
/**
 * <b>Constructor </B> <br>
 * @param cities
 */
	public PathManagerDistance(Country cities) {
		super(cities);
	}
/**
 * <b>Method</B> <br>
 * calculation the distance between two cities<br>
 * using a {@code City} coordinates <br>
 * the MyMath class provide this method in order to simplify the code structure <br>
 * 
 */
	@Override
	public double distance(City c1, City c2) {
		return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
	}

}
