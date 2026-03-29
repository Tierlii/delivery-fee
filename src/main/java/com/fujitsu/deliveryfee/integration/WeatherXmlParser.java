package com.fujitsu.deliveryfee.integration;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fujitsu.deliveryfee.domain.dto.ParsedWeatherObservation;

@Component
public class WeatherXmlParser {

    public List<ParsedWeatherObservation> parse(String xml) {
        List<ParsedWeatherObservation> observations = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            doc.getDocumentElement().normalize();

            NodeList stationNodes = doc.getElementsByTagName("station");

            for (int i = 0; i < stationNodes.getLength(); i++) {
                Node node = stationNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String name = getTagValue(element, "name");
                    double airTemp = parseDouble(getTagValue(element, "airtemperature"));
                    double windSpeed = parseDouble(getTagValue(element, "windspeed"));
                    String phenomenon = getTagValue(element, "phenomenon");

                    ParsedWeatherObservation observation = new ParsedWeatherObservation(
                            name,
                            airTemp,
                            windSpeed,
                            phenomenon
                    );

                    observations.add(observation);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse weather XML", e);
        }

        return observations;
    }

    private String getTagValue(Element element, String tagName) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list.getLength() == 0) return null;
        return list.item(0).getTextContent();
    }

    private double parseDouble(String value) {
        try {
            return value != null ? Double.parseDouble(value) : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}