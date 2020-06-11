package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import city.City;

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

		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		return cities;
	}

}