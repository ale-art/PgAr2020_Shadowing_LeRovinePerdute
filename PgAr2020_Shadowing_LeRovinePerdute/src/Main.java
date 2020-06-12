import java.util.Collection;

import city.City;
import city.Country;
import path.height.PathManagerHeight;
import utils.XmlManager;

public class Main {

    public static void main(String[] args) {
        // System.out.println(XmlManager.readCities("./input/PgAr_Map_5.xml"));
        Country theCountry = new Country(
                XmlManager.readCities("./input/PgAr_Map_10000.xml"));
        System.out.println("parsing avvenuto");
        long startTime = System.nanoTime();
        // System.out.println(new PathManagerAle(theCountry).getBestPath());
        Collection<City> cities = new PathManagerHeight(theCountry).getBestPath();
        for (City city : cities) {
            System.out.printf("-> %d ", city.getId());
        }
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}
