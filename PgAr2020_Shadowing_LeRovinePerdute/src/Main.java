import city.Country;
import path.distance.PathManagerAle;

import utils.XmlManager;

public class Main {
    public static void main(String[] args) {
        // System.out.println(XmlManager.readCities("./input/PgAr_Map_5.xml"));
        Country theCountry = new Country(
                XmlManager.readCities("./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_10000.xml"));
        System.out.println("parsing avvenuto");
        long startTime = System.nanoTime();
        System.out.println(new PathManagerAle(theCountry).getBestPath());
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}