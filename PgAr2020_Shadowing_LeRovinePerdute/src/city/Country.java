package city;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/** This class rapresent all the Country full of {@linkplain City} */
public class Country {

	private Map<Integer, City> cities = new HashMap<Integer, City>();

	/**
	 * @deprecated not recommended, bc then u have to add all {@linkplain City}
	 *             using {@link #add(City)} method
	 */
	public Country() {

	}

	/**
	 * Constructor
	 * 
	 * @param cities
	 *            the {@linkplain City}s to add to the country
	 */
	public Country(Collection<City> cities) {

		for (City city : cities) {
			this.cities.put(city.getId(), city);
		}
	}

	/**
	 * Constructor
	 * 
	 * @param cities
	 *            the {@linkplain City}s to add to the country
	 */
	public Country(City[] cities) {

		this(Arrays.asList(cities));
	}

	/** @return the City with that {@code id} */
	public City getCity(int id) {

		if (!contains(id)) {
			throw new IndexOutOfBoundsException();
		}

		return cities.get(id);
	}

	/**
	 * Add a {@linkplain City} to the {@link #cities}
	 * 
	 * @param city
	 * @return true if there was a {@linkplain City} with that id (the old city is
	 *         rewritten) else false
	 */
	public boolean add(City city) {

		return this.cities.put(city.getId(), city) == null;
	}

	/**
	 * Set a newCity with an id already used in {@link #cities}
	 * 
	 * @param newCity
	 */
	public City setCity(City newCity) {

		contains(newCity.getId());

		return cities.replace(newCity.getId(), newCity);
	}

	/**
	 * remove a city from {@link #cities}
	 * 
	 * @param index
	 *            is the City id to delete
	 */
	public boolean removeCity(int index) {

		return cities.remove(index) != null;
	}

	/**
	 * remove a city from {@link #cities}
	 * 
	 * @param toDel
	 *            is the City to delete
	 * @throws NullPointerException
	 *             if the city passed is null
	 */
	public boolean remove(City toDel) {

		Objects.requireNonNull(toDel);

		return removeCity(toDel.getId());
	}

	/** @return the all collection in {@link #cities} */
	public Collection<City> getCities() {

		return this.cities.values();
	}

	// public City[] toArray() {
	//
	// return (City[]) cities.toArray();
	// }
	/** @return the number of city present in {@link #cities} */
	public int size() {

		return cities.size();
	}

	/** @return true if {@link #cities} is empty, false otherwise */
	public boolean isEmpty() {

		return cities.isEmpty();
	}

	/** @return true if {@link #cities} contains that Object, false otherwise */
	public boolean contains(Object o) {

		return cities.containsValue(o);
	}

	/**
	 * @return true if {@link #cities} contains a City with that id, false otherwise
	 */
	public boolean contains(Integer id) {
		return cities.containsKey(id);
	}

	/** Clear all the {@link #cities} */
	public void clear() {

		cities.clear();
	}
}
