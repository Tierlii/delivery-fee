package com.fujitsu.deliveryfee.service;

import org.springframework.stereotype.Service;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.service.calculation.FeeCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryFeeService {

    private final WeatherDataService weatherDataService;
    private final FeeCalculator feeCalculator;

    public DeliveryFeeResponse calculateFee(City city, VehicleType vehicleType) {
        WeatherObservation weather = weatherDataService.getLatestWeatherForCity(city);

        double totalFee = feeCalculator.calculate(
                city,
                vehicleType,
                weather.getAirTemperature(),
                weather.getWindSpeed(),
                weather.getWeatherPhenomenon()
        );

        return DeliveryFeeResponse.builder()
                .city(city)
                .vehicleType(vehicleType)
                .fee(totalFee)
                .build();
    }
}