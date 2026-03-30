package com.fujitsu.deliveryfee.repository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;

@DataJpaTest
class WeatherObservationRepositoryTest {

    @Autowired
    private WeatherObservationRepository repository;

    @Test
    void shouldSaveAndFindLatestWeatherByCity() {
        WeatherObservation observation = new WeatherObservation();
        observation.setCity(City.TALLINN);
        observation.setAirTemperature(5.0);
        observation.setWindSpeed(3.0);
        observation.setWeatherPhenomenon("Clear");
        observation.setObservationTime(LocalDateTime.now());

        repository.save(observation);

        WeatherObservation result = repository
                .findTopByCityOrderByObservationTimeDesc(City.TALLINN)
                .orElse(null);

        assertNotNull(result);
        assertEquals(5.0, result.getAirTemperature());
    }
}