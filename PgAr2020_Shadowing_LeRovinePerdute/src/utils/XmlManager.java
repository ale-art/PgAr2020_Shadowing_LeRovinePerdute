package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import city.City;
import path.CostFunctionTypes;
import path.DijkstraCalculator;

public class XmlManager {
	public static ArrayList<City> readCities(String filePath) {
		ArrayList<City> cities = new ArrayList<City>();

		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmlr = null;

		try {
			xmlr = xmlif.createXMLStreamReader(filePath, new FileInputStream(filePath));

			while (xmlr.hasNext()) {
				if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT && xmlr.getLocalName().equals("city")) {
					int id = Integer.parseInt(xmlr.getAttributeValue(0));
					String name = xmlr.getAttributeValue(1);
					int x = Integer.parseInt(xmlr.getAttributeValue(2));
					int y = Integer.parseInt(xmlr.getAttributeValue(3));
					int h = Integer.parseInt(xmlr.getAttributeValue(4));

					City city = new City(id, x, y, h, name);

					xmlr.nextTag();

					while ((xmlr.getEventType() == XMLStreamConstants.START_ELEMENT)) {
						city.addCityId(Integer.parseInt(xmlr.getAttributeValue(0)));
						xmlr.nextTag();
						// ---------------
						xmlr.nextTag();
						// ---------------
					}

					cities.add(city);
				}

				xmlr.next();
			}

			xmlr.close();

		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		return cities;
	}

	public static void writePaths(ArrayList<City> cities, String outputPath) {
		XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
		XMLStreamWriter xmlw = null;

		try {
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(outputPath), "utf-8");
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		DijkstraCalculator calculator = new DijkstraCalculator(cities);

		try {
			xmlw.writeStartDocument("utf-8", "1.0");
			xmlw.writeStartElement("routes");

			for (int i = 0; i < 2; i++) {
				CostFunctionTypes type = i % 2 == 0 ? CostFunctionTypes.DISTANCE : CostFunctionTypes.HEIGHT;
				calculator.calc(cities.get(0), cities.get(cities.size() - 1), type);

				ArrayList<City> path = calculator.getPath();
				double cost = calculator.getTotalCost();
				String teamName = type == CostFunctionTypes.DISTANCE ? "Tonatiuh" : "Metztli";

				xmlw.writeStartElement("route");
				xmlw.writeAttribute("team", teamName);
				xmlw.writeAttribute("cost", String.format("%.2f", cost));
				xmlw.writeAttribute("cities", String.format("%d", path.size()));

				for (City city : path) {
					xmlw.writeStartElement("city");
					xmlw.writeAttribute("id", String.format("%d", city.getId()));
					xmlw.writeAttribute("name", city.getName());
					xmlw.writeEndElement();
				}

				xmlw.writeEndElement();
			}

			xmlw.writeEndElement();
			xmlw.close();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}