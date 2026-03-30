package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;

@Component
public class AirTemperatureRule implements ExtraFeeRule {

    @Override
    public double calculate(double airTemperature,
                            double windSpeed,
                            String weatherPhenomenon,
                            VehicleType vehicleType) {

        if (vehicleType == VehicleType.CAR) {
            return 0.0;
        }

        if (airTemperature < -10) {
            return 1.0;
        }

        if (airTemperature >= -10 && airTemperature <= 0) {
            return 0.5;
        }

        return 0.0;
    }
}