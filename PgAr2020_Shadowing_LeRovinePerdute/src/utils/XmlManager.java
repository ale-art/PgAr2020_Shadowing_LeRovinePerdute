package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import city.City;
import usingArrayCalcPath.CostFunctionTypes;
import usingArrayCalcPath.DijkstraCalculator;
import usingGraphCalcPath.AbstractPathManager;
import usingGraphCalcPath.distance.PathManagerDistance;
import usingGraphCalcPath.height.PathManagerHeight;

/**
 * Static class which manage the reading / writing of XML files of the program
 */
public class XmlManager {
	/**
	 * Read the cities contained in the specified file
	 * 
	 * @param filePath
	 *            the path of the file into which the information are contained
	 * @return the {@code ArrayList<City>} which contains the information read from
	 *         the specified file
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static ArrayList<City> readCities(String filePath) throws XMLStreamException, FileNotFoundException {
		ArrayList<City> cities = new ArrayList<City>();

		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlr = null;

		try {
			xmlr = xmlif.createXMLStreamReader(filePath, new FileInputStream(filePath));

			while (xmlr.hasNext()) {
				/**
				 * For each start element with name "city" it took its data and save them into
				 * the cities ArrayList
				 */
				if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("city")) {
					int id = Integer.parseInt(xmlr.getAttributeValue(0));
					String name = xmlr.getAttributeValue(1);
					int x = Integer.parseInt(xmlr.getAttributeValue(2));
					int y = Integer.parseInt(xmlr.getAttributeValue(3));
					int h = Integer.parseInt(xmlr.getAttributeValue(4));

					City city = new City(id, x, y, h, name);

					xmlr.nextTag();

					// Read and save the ids of the neighbour of each city
					while ((xmlr.getEventType() == XMLStreamConstants.START_ELEMENT)) {
						city.addCityId(Integer.parseInt(xmlr.getAttributeValue(0)));
						xmlr.nextTag();
						xmlr.nextTag();
					}

					cities.add(city);
				}

				xmlr.next();
			}

			xmlr.close();

		} catch (FileNotFoundException | XMLStreamException e) {
			throw e;
		}

		return cities;
	}

	/**
	 * Write a city element with the specified {@code XMLStreamWriter} in the xml
	 * file
	 * 
	 * @param xmlw
	 *            the writer
	 * @param city
	 *            the city to write
	 * @throws XMLStreamException
	 */
	private static void writePathCity(XMLStreamWriter xmlw, City city) throws XMLStreamException {
		try {
			// Write the element with all its attributes
			xmlw.writeStartElement("city");
			xmlw.writeAttribute("id", String.format("%d", city.getId()));
			xmlw.writeAttribute("name", city.getName());
			xmlw.writeEndElement();

		} catch (XMLStreamException e) {
			throw e;
		}
	}

	/**
	 * Calculate and write the best path that each team have to follow for reach the
	 * last city with less cost
	 * 
	 * @param cities
	 *            the cities that the teams can reach
	 * @param outputPath
	 *            the path of the output file into which the final information are
	 *            writed
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 * @see #writePathsUsingAbrastracPathManager(ArrayList, String)
	 */
	public static void writePaths(ArrayList<City> cities, String outputPath)
			throws XMLStreamException, FileNotFoundException {

		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlw = null;

		try {
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(outputPath), "utf-8");
		} catch (FileNotFoundException | XMLStreamException e) {
			throw e;
		}

		// To calculate the best path it use the DijkstraCalculator
		DijkstraCalculator calculator = new DijkstraCalculator(cities);

		try {
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeStartElement("routes");

			/**
			 * Evaluate the best path for each team (in this case 2) beacause each team have
			 * a different cost function
			 */
			for (int i = 0; i < 2; i++) {
				// Select the cost function
				CostFunctionTypes type = i % 2 == 0 ? CostFunctionTypes.DISTANCE : CostFunctionTypes.HEIGHT;
				// Execute the Dijkstra algorithm
				calculator.calc(cities.get(0), cities.get(cities.size() - 1), type);

				// Get the calculated information
				ArrayList<City> path = calculator.getPath();
				double cost = calculator.getTotalCost();
				String teamName = type == CostFunctionTypes.DISTANCE ? "Tonatiuh" : "Metztli";

				// Write the route information
				xmlw.writeStartElement("route");
				xmlw.writeAttribute("team", teamName);
				xmlw.writeAttribute("cost", String.format("%.2f", cost));
				xmlw.writeAttribute("cities", String.format("%d", path.size()));

				// Write each step of the path
				for (City city : path) {
					writePathCity(xmlw, city);
				}

				xmlw.writeEndElement();
			}

			xmlw.writeEndElement();
			xmlw.close();

		} catch (XMLStreamException e) {
			throw e;
		}
	}

	/**
	 * Calculate and write the best path that each team have to follow for reach the
	 * last city with less cost<br>
	 * <br>
	 * 
	 * This Method is different from {@link #writePaths(ArrayList, String)} because
	 * these one use {@linkplain AbrastracPathManager} & his son (ex
	 * {@linkplain PathManagerDistance} & {@linkplain PathManagerHeight})
	 * 
	 * 
	 * @param cities
	 *            the cities that the teams can reach
	 * @param outputPath
	 *            the path of the output file into which the final information are
	 *            writed
	 * @throws XMLStreamException
	 * @throws FileNotFoundException
	 */
	public static void writePathsUsingAbrastracPathManager(ArrayList<City> cities, String outputPath)
			throws XMLStreamException, FileNotFoundException {

		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlw = null;
		try {
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(outputPath), "utf-8");
		} catch (FileNotFoundException | XMLStreamException e) {
			throw e;
		}

		try {
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeStartElement("routes");

			/**
			 * Evaluate the best path for each team (in this case 2) beacause each team have
			 * a different cost function
			 */
			City lastCity = cities.get(cities.size() - 1);

			for (int i = 0; i < 2; i++) {
				// Select the cost function
				AbstractPathManager pathManager = i % 2 == 0 ? new PathManagerDistance(cities)
						: new PathManagerHeight(cities);
				// Execute the Dijkstra algorithm
				Collection<City> path = pathManager.getBestPath(lastCity);
				// Get the calculated information
				double cost = pathManager.costPath(lastCity);
				String teamName = pathManager.getClass() == PathManagerDistance.class ? "Tonatiuh" : "Metztli";

				// Write the route information
				xmlw.writeStartElement("route");
				xmlw.writeAttribute("team", teamName);
				xmlw.writeAttribute("cost", String.format("%.2f", cost));
				xmlw.writeAttribute("cities", String.format("%d", path.size()));

				// Write each step of the path
				for (City city : path) {
					writePathCity(xmlw, city);
				}

				xmlw.writeEndElement();
			}

			xmlw.writeEndElement();
			xmlw.close();

		} catch (XMLStreamException e) {
			throw e;
		}
	}
}