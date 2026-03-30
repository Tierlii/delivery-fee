package com.fujitsu.deliveryfee.service.calculation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExtraFeeCalculator {

    private final List<ExtraFeeRule> extraFeeRules;

    public double calculateExtraFee(VehicleType vehicleType, WeatherObservation weatherObservation) {
        return extraFeeRules.stream()
                .mapToDouble(rule -> rule.calculateExtraFee(vehicleType, weatherObservation))
                .sum();
    }
}