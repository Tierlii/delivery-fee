package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeeCalculator {

    private final BaseFeeCalculator baseFeeCalculator;
    private final ExtraFeeCalculator extraFeeCalculator;

    public double calculateFee(City city, VehicleType vehicleType, WeatherObservation weatherObservation) {
        double baseFee = baseFeeCalculator.calculateBaseFee(city, vehicleType);
        double extraFee = extraFeeCalculator.calculateExtraFee(vehicleType, weatherObservation);

        return baseFee + extraFee;
    }
}