package path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import city.City;
import city.Country;

public abstract class AbstractPathManager {

	private boolean[][] linkMatrix;

	private double[][] distanceMatrix;

	private Set<Integer> nodeListToAnalize=new TreeSet<Integer>();

	private Set<Integer> nodeListAlreadyVisit=new TreeSet<Integer>();

	private double[] pathEffortFromS;

	private int[] previus;

	private City S;

	public abstract double distance(City c1, City c2);

	protected AbstractPathManager(Country cities) {

		setMatrici(cities);

		S = cities.getCity(0);

		nodeListToAnalize.add(S.getId());

		setPrevius(cities.size());

		setPathEffort(cities.size());
	}

	protected AbstractPathManager(Collection<City> cities) {

		this(new Country(cities));
	}

	protected AbstractPathManager(City[] cities) {

		this(new Country(cities));
	}

	private void setMatrici(Country cities) {
		int matrixlong = cities.size();

		linkMatrix = new boolean[matrixlong][matrixlong];

		distanceMatrix = new double[matrixlong][matrixlong];

		Iterator<City> city = cities.iterator();
		for (int i = 0; i < matrixlong; i++) {

			Collection<Integer> linkCities = city.next().getLinkedCities();

			for (Integer integer : linkCities) {

				linkMatrix[i][integer] = true;

				distanceMatrix[i][integer] = distance(cities.getCity(i), cities.getCity(integer));
			}
		}
	}

	private void setPrevius(int citiesLong) {
		previus = new int[citiesLong];
		for (int i = 0; i < previus.length; i++) {
			previus[i] = i;
		}
	}

	private void setPathEffort(int citiesLong) {
		pathEffortFromS = new double[citiesLong];
		pathEffortFromS[0] = S.getId();
		
	}

	public  void calculate() {
		
									
//				while(!nodeListToAnalize.isEmpty()) {
//					Integer nodo=nodeListToAnalize.iterator().next();
//					nodeListToAnalize.remove(nodo);
//					System.out.println("nodo = "+nodo);
//					List<Integer> ns = new ArrayList<Integer>() ;
//					 
//					for (int j = 0; j < previus.length; j++) {
//						if(linkMatrix[nodo][j]==true)
//							ns.add(j);
//					}
//					System.out.println("nodi in uscita = "+Arrays.toString(ns.toArray()));
//					
//					while(!ns.isEmpty()) {
//						int nodo1=ns.get(0);
//						ns.remove(0);
//						System.out.println(" nodo in uscita "+nodo1);
//						double calc=0;
//						if(pathEffortFromS[nodo]!=0) 
//							calc = pathEffortFromS[nodo]+distanceMatrix[nodo][nodo1];
//						else 
//							calc=distanceMatrix[nodo][nodo1];
//						
//						System.out.println(" costo "+Arrays.toString()+"+"+c[n,n1]+ "="+cc);
//					if(pathEffortFromS[nodo1]==0||calc<pathEffortFromS[nodo1])
//						pathEffortFromS[ nodo1]=calc;
//					previus[nodo1]=nodo;
//					nodeListToAnalize.add(nodo1);
				//}
					
					}
	}

}
