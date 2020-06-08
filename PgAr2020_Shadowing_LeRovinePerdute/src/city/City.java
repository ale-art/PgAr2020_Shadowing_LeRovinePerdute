package city;

import java.util.ArrayList;

public class City {
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
        return String.format("%s (%d, %d, %d)", name, x, y, h);
    }
}