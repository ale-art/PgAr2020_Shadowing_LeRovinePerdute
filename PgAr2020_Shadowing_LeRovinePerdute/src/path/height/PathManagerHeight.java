package path.height;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import city.City;
import city.Country;
import path.AbstractPathManager;

public class PathManagerHeight extends AbstractPathManager {

	public PathManagerHeight(Country cities) {
		super(cities);
	}

	@Override
	public double distance(City c1, City c2) {
		
		if (c1.getH()>c2.getH())
			return c1.getH()-c2.getH();
		
		return c2.getH()-c1.getH();
	}

	
	
}
