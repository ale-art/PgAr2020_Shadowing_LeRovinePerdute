package path;

import java.util.ArrayList;

import city.City;
import it.unibs.fp.mylib.MyMath;
import path.distance.PathManagerAle;
import utils.XmlManager;

public class ProvaDijkstra {

    private static int getMinId(double[] distances, ArrayList<City> cities) {
        double min = Double.POSITIVE_INFINITY;
        int minId = 0;

        for (City city : cities) {
            int id = city.getId();

            if (distances[id] < min) {
                min = distances[id];
                minId = id;
            }
        }

        return minId;
    }

    private static City getCityById(ArrayList<City> cities, int id) {
        return cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
    }

    private static double getDistance(City c1, City c2) {
        return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
    }

    public static ArrayList<City> calcDijkstra(ArrayList<City> cities, City source, City destination) {
        ArrayList<City> newList = new ArrayList<>();
        double[] distances = new double[cities.size()];
        int[] previousId = new int[cities.size()];

        for (City city : cities) {
            int id = city.getId();

            distances[id] = Double.POSITIVE_INFINITY;
            previousId[id] = -1;

            newList.add(city);
        }

        distances[source.getId()] = 0;

        while (!newList.isEmpty()) {
            City c = getCityById(cities, getMinId(distances, newList));
            newList.remove(c);

            for (int ids : c.getLinkedCities()) {
                City neighbour = getCityById(cities, ids);

                if (!newList.contains(neighbour)) {
                    continue;
                }

                double calcDist = distances[c.getId()] + getDistance(c, neighbour);

                if (calcDist < distances[neighbour.getId()]) {
                    distances[neighbour.getId()] = calcDist;
                    previousId[neighbour.getId()] = c.getId();
                }
            }
        }

        int startId = previousId.length - 1;
        while (previousId[startId] != -1) {
            System.out.printf("-> %d ", startId);
            startId = previousId[startId];
        }

        return null;
    }

    public static void main(String[] args) {
        /*
         * ArrayList<City> aaa = XmlManager.readCities(
         * "./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_5.xml");
         * System.out.println(calcDijkstra(aaa, aaa.get(0), aaa.get(aaa.size() - 1)));
         */

        ArrayList<City> aaa = XmlManager.readCities("./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_10000.xml");
        long startTime = System.nanoTime();
        System.out.println(calcDijkstra(aaa, aaa.get(0), aaa.get(aaa.size() - 1)));
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
    }
}