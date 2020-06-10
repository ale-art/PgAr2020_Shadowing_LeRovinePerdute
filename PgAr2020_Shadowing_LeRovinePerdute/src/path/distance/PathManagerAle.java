package path.distance;

import java.util.Collection;

import city.City;
import city.Country;
import it.unibs.fp.mylib.MyMath;
import path.AbstractPathManager;

public class PathManagerAle extends AbstractPathManager {

	public PathManagerAle(Country cities) {
		super(cities);
	}
	
	@Override
	public double distance(City c1,City c2) {
		return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
	}

}
