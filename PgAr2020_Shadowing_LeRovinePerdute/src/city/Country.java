package city;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import java.util.Objects;
import java.util.TreeSet;

public class Country {

	Collection<City> cities;

	public Country() {

		cities = new TreeSet<City>();
	}

	public Country(Collection<City> cities) {

		this.cities = new TreeSet<City>(cities);
		System.out.println("copia avvenuta");
	}

	public Country(City[] cities) {

		this(Arrays.asList(cities));
	}

	public City getCity(int id) {

		rangeCheck(id);

		Iterator<City> itr = cities.iterator();

		while (itr.hasNext()) {

			City _this = itr.next();

			if (_this.getId() == id)
				return _this;
		}
		return null;
	}

	public boolean add(City city) {

		return this.cities.add(city);
	}

	public boolean addAll(Collection<? extends City> c) {

		return this.cities.addAll(cities);
	}

	private void rangeCheck(int id) {
		if (id > cities.size() - 1 || id < 0)
			throw new IndexOutOfBoundsException("The id doesn't exist");

		if (cities.isEmpty())
			throw new NullPointerException("The Collection Country is empty");
	}

	public City setCity(City newCity, int index) {

		rangeCheck(index);

		City old = getCity(index);

		//it shouldn't happened
		if (old == null) {
			throw new NullPointerException();
		}

		cities.remove(old);

		cities.add(newCity);

		return old;
	}

	public boolean remove(Object toDel) {

		Objects.requireNonNull(toDel);

		return cities.remove(toDel);
	}

	public boolean removeCity(int index) {

		return remove(getCity(index));
	}

	public Collection<City> getCities() {

		return this.cities;
	}

//	public City[] toArray() {
//
//		return (City[]) cities.toArray();
//	}

	public int size() {

		return cities.size();
	}

	public Iterator<City> iterator() {

		return cities.iterator();
	}

	public boolean isEmpty() {

		return cities.isEmpty();
	}

	public boolean contains(Object o) {

		return cities.contains(o);
	}

	public void clear() {

		cities = new HashSet<City>();
	}
}
