package com.fujitsu.deliveryfee.integration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.model.ParsedWeatherObservation;
import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;
import com.fujitsu.deliveryfee.service.WeatherImportService;

@ExtendWith(MockitoExtension.class)
class WeatherImportServiceTest {

    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private WeatherXmlParser weatherXmlParser;

    @Mock
    private StationToCityMapper stationToCityMapper;

    @Mock
    private WeatherObservationRepository repository;

    @InjectMocks
    private WeatherImportService service;

    @Test
    void shouldImportWeatherData() {
        String xml = "<observations></observations>";

        ParsedWeatherObservation parsed = new ParsedWeatherObservation();
        parsed.setStationName("Tartu-Tõravere");
        parsed.setWmoCode("26242");
        parsed.setAirTemperature(-2.1);
        parsed.setWindSpeed(4.7);
        parsed.setWeatherPhenomenon("Light snow");
        parsed.setObservationTime(LocalDateTime.now());

        when(weatherApiClient.fetchWeatherData()).thenReturn(xml);
        when(weatherXmlParser.parse(xml)).thenReturn(List.of(parsed));
        when(stationToCityMapper.mapToCity("Tartu-Tõravere"))
        .thenReturn(Optional.of(City.TARTU));
        service.importWeatherData();

        verify(repository, times(1)).save(ArgumentMatchers.any());
    }
}