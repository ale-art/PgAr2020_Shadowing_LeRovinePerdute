package slowCalcPath;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import city.City;

public class Country {

	Map<Integer,City> cities=new HashMap<Integer,City>();

	public Country() {

	} 

	public Country(Collection<City> cities) {

		for (City city : cities) {
			this.cities.put(city.getId(), city);
		}
	}

	public Country(City[] cities) {

		this(Arrays.asList(cities));
	}

	public City getCity(int id) {

		rangeCheck(id);

		return cities.get(id);
	}

	public boolean add(City city) {

		return this.cities.put(city.getId(), city)==null;
	}

	

	private void rangeCheck(int id) {
		if (id > cities.size() - 1 || id < 0)
			throw new IndexOutOfBoundsException("The id doesn't exist");

		if (cities.isEmpty())
			throw new NullPointerException("The Collection Country is empty");
	}

	public City setCity(int index,City newCity) {

		rangeCheck(index);

		return cities.replace(index, newCity);
	}

	public boolean removeCity(int index) {

		return cities.remove(index)!=null;
	}
	public boolean remove(City toDel) {

		Objects.requireNonNull(toDel);

		
	return removeCity(toDel.getId());
	}

	public Collection<City> getCities() {

		return this.cities.values();
	}

//	public City[] toArray() {
//
//		return (City[]) cities.toArray();
//	}

	public int size() {

		return cities.size();
	}


	public boolean isEmpty() {

		return cities.isEmpty();
	}

	public boolean contains(Object o) {

		return cities.containsValue(o);
	}
	
	public boolean contains(Integer i) {
		return cities.containsKey(i);
	}

	public void clear() {

		cities.clear();
	}
}

