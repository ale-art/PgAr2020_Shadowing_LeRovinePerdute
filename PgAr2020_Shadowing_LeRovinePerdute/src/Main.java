import city.Country;
import path.distance.PathManagerAle;

import utils.XmlManager;

public class Main {
    public static void main(String[] args) {
       //System.out.println(XmlManager.readCities("./input/PgAr_Map_5.xml"));
    	Country theCountry= new Country(XmlManager.readCities("./input/PgAr_Map_2000.xml"));
    	System.out.println("parsing avvenuto");
    	System.out.println(new PathManagerAle(theCountry).getBestPath());
    }
}