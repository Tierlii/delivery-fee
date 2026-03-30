package com.fujitsu.deliveryfee.service;

import org.springframework.stereotype.Service;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherDataService {

    private final WeatherObservationRepository weatherObservationRepository;

    public WeatherObservation getLatestWeatherForCity(City city) {
        return weatherObservationRepository
                .findTopByCityOrderByObservationTimeDesc(city)
                .orElseThrow(() ->
                    new IllegalStateException("No weather data found for city: " + city)
    );
    }
}