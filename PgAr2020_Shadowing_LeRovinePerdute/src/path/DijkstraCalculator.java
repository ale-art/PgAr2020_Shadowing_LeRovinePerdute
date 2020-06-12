package path;

import java.util.ArrayList;
import java.util.Collections;

import city.City;
import it.unibs.fp.mylib.MyMath;
import utils.XmlManager;

public class DijkstraCalculator {
    private ArrayList<City> cities;
    private ArrayList<City> path;
    private double totalCost;

    public DijkstraCalculator(ArrayList<City> cities) {
        this.cities = cities;
        this.path = new ArrayList<>();
        totalCost = 0;
    }

    public DijkstraCalculator() {
        this.cities = new ArrayList<>();
        this.path = new ArrayList<>();
        totalCost = 0;
    }

    private int getMinId(double[] distances, ArrayList<City> remainderCities) {
        double min = distances[remainderCities.get(0).getId()];
        int minId = remainderCities.get(0).getId();

        for (int i = 1; i < remainderCities.size(); i++) {
            int id = remainderCities.get(i).getId();

            if (distances[id] < min) {
                min = distances[id];
                minId = id;
            }
        }

        return minId;
    }

    private City getCityById(int id) {
        return this.cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
    }

    private double getDistance(City c1, City c2) {
        return MyMath.distance(c1.getX(), c2.getX(), c1.getY(), c2.getY());
    }

    private double getHeightDifference(City c1, City c2) {
        return Math.abs(c1.getH() - c2.getH());
    }

    public void calc(City source, City destination, CostFunctionTypes type) {
        ArrayList<City> newList = new ArrayList<>();
        double[] distances = new double[this.cities.size()];
        int[] previousId = new int[this.cities.size()];

        for (City city : this.cities) {
            int id = city.getId();

            distances[id] = Double.POSITIVE_INFINITY;
            previousId[id] = -1;

            newList.add(city);
        }

        distances[source.getId()] = 0;

        while (!newList.isEmpty()) {
            City c = getCityById(getMinId(distances, newList));
            newList.remove(c);

            for (int ids : c.getLinkedCities()) {
                City neighbour = getCityById(ids);

                if (!newList.contains(neighbour)) {
                    continue;
                }

                double distance = type == CostFunctionTypes.HEIGHT ? getHeightDifference(c, neighbour)
                        : getDistance(c, neighbour);

                double calcDist = distances[c.getId()] + distance;

                if (calcDist < distances[neighbour.getId()]) {
                    distances[neighbour.getId()] = calcDist;
                    previousId[neighbour.getId()] = c.getId();
                }
            }
        }

        ArrayList<City> path = new ArrayList<>();

        int startId = destination.getId();
        while (previousId[startId] != -1) {
            path.add(getCityById(startId));
            startId = previousId[startId];
        }

        path.add(getCityById(source.getId()));
        Collections.reverse(path);

        this.path = path;
        this.totalCost = distances[destination.getId()];
    }

    public ArrayList<City> getPath() {
        return this.path;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public static void main(String[] args) {
        /*
         * ArrayList<City> aaa = XmlManager.readCities(
         * "./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_50.xml"); long startTime
         * = System.nanoTime(); ArrayList<City> path = calcDijkstra(aaa, aaa.get(0),
         * aaa.get(aaa.size() - 1), CostFunctionTypes.HEIGHT); long stopTime =
         * System.nanoTime(); System.out.println(stopTime - startTime);
         * 
         * for (City city : path) { System.out.printf("-> %d ", city.getId()); }
         */

        ArrayList<City> cities = XmlManager.readCities("./PgAr2020_Shadowing_LeRovinePerdute/input/PgAr_Map_10000.xml");
        DijkstraCalculator calculator = new DijkstraCalculator(cities);

        long startTime = System.nanoTime();
        calculator.calc(cities.get(0), cities.get(cities.size() - 1), CostFunctionTypes.HEIGHT);
        long stopTime = System.nanoTime();

        System.out.printf("Calculated in %f s\n", (stopTime - startTime) / Math.pow(10, 9));

        for (City city : calculator.getPath()) {
            System.out.printf("-> %d ", city.getId());
        }
    }
}