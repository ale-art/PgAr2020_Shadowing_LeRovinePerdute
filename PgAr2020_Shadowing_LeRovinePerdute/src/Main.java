import java.util.ArrayList;

import city.City;
import utils.XmlManager;

public class Main {

    public static final int[] FILE_INDEXES = { 5, 12, 50, 200, 2000, 10000 };
    public static final String BASE_INPUT_PATH = "./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_%d.xml";
    public static final String BASE_OUTPUT_PATH = "./PgAr2020_Shadowing_LeRovinePerdute/output/Routes_%d.xml";

    public static void main(String[] args) {
        System.out.printf("Starting the application\n\n");

        for (int i = 0; i < FILE_INDEXES.length; i++) {
            System.out.printf("Evaluating %s\n", String.format(BASE_INPUT_PATH, FILE_INDEXES[i]));

            long startTime = System.nanoTime();

            ArrayList<City> cities = XmlManager.readCities(String.format(BASE_INPUT_PATH, FILE_INDEXES[i]));
            XmlManager.writePaths(cities, String.format(BASE_OUTPUT_PATH, FILE_INDEXES[i]));

            long stopTime = System.nanoTime();

            System.out.printf("Execution completed in %.3f seconds\n\n", (stopTime - startTime) / Math.pow(10, 9));
        }
    }
}
