package com.fujitsu.deliveryfee.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherApiClient {

    private static final String WEATHER_API_URL =
            "https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php";

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchWeatherData() {
        return restTemplate.getForObject(WEATHER_API_URL, String.class);
    }
}