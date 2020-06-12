package city;

import java.util.ArrayList;

/**
 * <b>Class</b> <br>
 * a public class which implements {@link Comparable} <br>
 * 
 *
 */
public class City implements Comparable<City> {

	private int id;
	/**
	 * <b>Attribute</b> <br>
	 * the the x-axis
	 */
	private int x;
	/**
	 * <b> Attribute </B> <br>
	 * the y-axis
	 * 
	 */
	private int y;
	/**
	 * <b>Attribute</b> <br>
	 * the height
	 */
	private int h;

	private String name;
	/**
	 * <b>Attribute</b> <br>
	 * linked {@code City}
	 */
	private ArrayList<Integer> linkedCitiesIds;

	/**
	 * <b>Constructor</B> <br>
	 * all the attributes are provided by the {@code XmlManager} <br>
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param h
	 * @param name
	 * 
	 */
	public City(int id, int x, int y, int h, String name) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.h = h;
		this.name = name;

		this.linkedCitiesIds = new ArrayList<Integer>();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getH() {
		return h;
	}

	public int getId() {
		return id;
	}

	public int hashCode() {
		return id;
	}

	/**
	 * <b>Method</b> <br>
	 * 
	 * @return true if the two {@link City} are equals <br>
	 *         otherwise false <br>
	 */
	public boolean equals(Object city) {

		if (city instanceof City) {

			City c = (City) city;

			return this.hashCode() == c.hashCode() || (this.x == c.getX() && this.y == c.getY() && this.h == c.getH());
		}

		return false;
	}

	public String getName() {

		return name;
	}

	public ArrayList<Integer> getLinkedCities() {

		return linkedCitiesIds;
	}

	public void addCityId(int cityId) {
		this.linkedCitiesIds.add(cityId);
	}

	public void addCitiesIds(ArrayList<Integer> citiesIds) {
		this.linkedCitiesIds.addAll(citiesIds);
	}

	@Override
	public String toString() {
		return String.format("%d %s (%d, %d, %d)%s", id, name, x, y, h, linkedCitiesIds);
	}

	@Override
	public int compareTo(City o) {
		return o.getId() - id;

	}
}