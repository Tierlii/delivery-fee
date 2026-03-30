package com.fujitsu.deliveryfee.service;

import org.springframework.stereotype.Service;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.service.calculation.BaseFeeCalculator;
import com.fujitsu.deliveryfee.service.calculation.ExtraFeeCalculator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryFeeService {

    private final WeatherDataService weatherDataService;
    private final BaseFeeCalculator baseFeeCalculator;
    private final ExtraFeeCalculator extraFeeCalculator;

    public DeliveryFeeResponse calculateFee(City city, VehicleType vehicleType) {
        WeatherObservation weather = weatherDataService.getLatestWeatherForCity(city);

        double baseFee = baseFeeCalculator.calculateBaseFee(city, vehicleType);
        double extraFee = extraFeeCalculator.calculateExtraFee(vehicleType, weather);
        double totalFee = baseFee + extraFee;

        return DeliveryFeeResponse.builder()
                .city(city)
                .vehicleType(vehicleType)
                .fee(totalFee)
                .build();
    }

    
}