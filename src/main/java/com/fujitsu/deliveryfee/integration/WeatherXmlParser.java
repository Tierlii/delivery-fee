package com.fujitsu.deliveryfee.integration;

import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fujitsu.deliveryfee.domain.model.ParsedWeatherObservation;

@Component
public class WeatherXmlParser {

    public List<ParsedWeatherObservation> parse(String xml) {
        List<ParsedWeatherObservation> observations = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            LocalDateTime observationTime = parseObservationTime(root);

            NodeList stationNodes = document.getElementsByTagName("station");

            for (int i = 0; i < stationNodes.getLength(); i++) {
                Node node = stationNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element stationElement = (Element) node;

                    String stationName = getTagValue(stationElement, "name");
                    String wmoCode = getTagValue(stationElement, "wmocode");
                    Double airTemperature = parseDouble(getTagValue(stationElement, "airtemperature"));
                    Double windSpeed = parseDouble(getTagValue(stationElement, "windspeed"));
                    String weatherPhenomenon = getTagValue(stationElement, "phenomenon");

                    ParsedWeatherObservation observation = new ParsedWeatherObservation(
                            stationName,
                            wmoCode,
                            airTemperature,
                            windSpeed,
                            weatherPhenomenon,
                            observationTime
                    );

                    observations.add(observation);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse weather XML", e);
        }

        return observations;
    }

    private LocalDateTime parseObservationTime(Element root) {
        String timestamp = root.getAttribute("timestamp");

        if (timestamp == null || timestamp.isBlank()) {
            return null;
        }

        try {
            long epochSeconds = Long.parseLong(timestamp);
            return LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(epochSeconds),
                    ZoneId.systemDefault()
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getTagValue(Element element, String tagName) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list.getLength() == 0) {
            return null;
        }

        String value = list.item(0).getTextContent();
        return value != null ? value.trim() : null;
    }

    private Double parseDouble(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}