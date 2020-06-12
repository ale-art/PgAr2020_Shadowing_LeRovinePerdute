package slowCalcPath.height;

import city.City;
import slowCalcPath.AbstractPathManager;
import slowCalcPath.Country;

public class PathManagerHeight extends AbstractPathManager {
	/**
	 * <b>Constructor</B> <br>
	 * inherits from the {@link AbstractPathManager} <br>
	 * 
	 * @param cities
	 */
	public PathManagerHeight(Country cities) {
		super(cities);
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
