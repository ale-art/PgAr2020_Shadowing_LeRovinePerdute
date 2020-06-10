import city.Country;
import path.height.PathManagerHeight;
import utils.XmlManager;

public class Main {
    public static void main(String[] args) {
       System.out.println(XmlManager.readCities("./input/PgAr_Map_5.xml"));
//    	Country theCountry= new Country(XmlManager.readCities("./input/PgAr_Map_5.xml"));
//    	System.out.println(new PathManagerHeight(theCountry).getBestPath());
    }
}