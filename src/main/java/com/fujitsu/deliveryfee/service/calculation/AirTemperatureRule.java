package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

@Component
public class AirTemperatureRule implements ExtraFeeRule {

    @Override
    public double calculateExtraFee(VehicleType vehicleType, WeatherObservation weatherObservation) {
        if (vehicleType != VehicleType.SCOOTER && vehicleType != VehicleType.BIKE) {
            return 0.0;
        }

        Double airTemperature = weatherObservation.getAirTemperature();
        if (airTemperature == null) {
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