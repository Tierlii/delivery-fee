package com.fujitsu.deliveryfee.service;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.exception.WeatherDataNotFoundException;
import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;

@ExtendWith(MockitoExtension.class)
class WeatherDataServiceTest {

    @Mock
    private WeatherObservationRepository repository;

    @InjectMocks
    private WeatherDataService service;

    @Test
    void shouldReturnLatestWeather() {
        WeatherObservation observation = new WeatherObservation();
        observation.setCity(City.TARTU);
        observation.setAirTemperature(2.0);
        observation.setWindSpeed(4.0);
        observation.setWeatherPhenomenon("Clear");
        observation.setObservationTime(LocalDateTime.now());

        when(repository.findTopByCityOrderByObservationTimeDesc(City.TARTU))
                .thenReturn(Optional.of(observation));

        WeatherObservation result = service.getLatestWeatherForCity(City.TARTU);

        assertNotNull(result);
        assertEquals(2.0, result.getAirTemperature());
    }

    @Test
    void shouldThrowExceptionWhenNoWeatherData() {
        when(repository.findTopByCityOrderByObservationTimeDesc(City.TARTU))
                .thenReturn(Optional.empty());

        assertThrows(WeatherDataNotFoundException.class,
                () -> service.getLatestWeatherForCity(City.TARTU));
    }
}