package com.fujitsu.deliveryfee.integration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeliveryFeeIntegrationTest {

    @Autowired
    private WeatherObservationRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void shouldCalculateFeeThroughApi() {
        WeatherObservation observation = new WeatherObservation();
        observation.setCity(City.TARTU);
        observation.setAirTemperature(-5.0);
        observation.setWindSpeed(10.0);
        observation.setWeatherPhenomenon("Light snow");
        observation.setObservationTime(LocalDateTime.now());

        repository.save(observation);

        String url = "http://localhost:" + port +
                "/api/v1/delivery-fee?city=TARTU&vehicleType=BIKE";

        String response = restTemplate.getForObject(url, String.class);

        assertNotNull(response);
        assertTrue(response.contains("4.5"));
    }
}