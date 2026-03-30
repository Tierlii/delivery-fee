package com.fujitsu.deliveryfee.service;

import org.springframework.stereotype.Service;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryFeeService {

    private final WeatherDataService weatherDataService;

    public DeliveryFeeResponse calculateFee(City city, VehicleType vehicleType) {

        WeatherObservation weather = weatherDataService.getLatestWeatherForCity(city);

        double totalFee = 0.0;

        return DeliveryFeeResponse.builder()
                .city(city)
                .vehicleType(vehicleType)
                .fee(totalFee)
                .build();
    }
}