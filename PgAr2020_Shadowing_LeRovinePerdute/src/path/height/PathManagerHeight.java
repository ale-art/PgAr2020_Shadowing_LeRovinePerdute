package path.height;


import city.City;
import city.Country;
import path.AbstractPathManager;

public class PathManagerHeight extends AbstractPathManager {

	public PathManagerHeight(Country cities) {
		super(cities);
	}

	@Override
	public double distance(City c1, City c2) {
		return Math.abs(c1.getH()-c2.getH());
	}

	
}
