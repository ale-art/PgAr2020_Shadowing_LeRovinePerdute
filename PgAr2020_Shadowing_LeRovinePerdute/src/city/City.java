package city;

import java.util.ArrayList;

public class City implements Comparable<City>{
	private int id;
	private int x;
	private int y;
	private int h;
	private String name;
	private ArrayList<Integer> linkedCitiesIds;

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
		return String.format("%d %s (%d, %d, %d)%s", id,name, x, y, h,linkedCitiesIds);
	}

	@Override
	public int compareTo(City o) {
		return o.getId()-id;
		
	}
}