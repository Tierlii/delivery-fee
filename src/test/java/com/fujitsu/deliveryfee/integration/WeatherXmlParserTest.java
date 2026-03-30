package com.fujitsu.deliveryfee.integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.model.ParsedWeatherObservation;

class WeatherXmlParserTest {

    private final WeatherXmlParser parser = new WeatherXmlParser();

    @Test
    void shouldParseXml() {
        String xml = """
                <observations>
                    <station>
                        <name>Tartu-Tõravere</name>
                        <wmocode>123</wmocode>
                        <airtemperature>-2.1</airtemperature>
                        <windspeed>4.7</windspeed>
                        <phenomenon>Light snow</phenomenon>
                    </station>
                </observations>
                """;

        List<ParsedWeatherObservation> result = parser.parse(xml);

        assertFalse(result.isEmpty());
        assertEquals("Light snow", result.get(0).getWeatherPhenomenon());
    }
}