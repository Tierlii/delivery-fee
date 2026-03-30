package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

@Component
public class WeatherPhenomenonRule implements ExtraFeeRule {

    @Override
    public double calculateExtraFee(VehicleType vehicleType, WeatherObservation weatherObservation) {
        if (vehicleType != VehicleType.SCOOTER && vehicleType != VehicleType.BIKE) {
            return 0.0;
        }

        String phenomenon = weatherObservation.getWeatherPhenomenon();
        if (phenomenon == null || phenomenon.isBlank()) {
            return 0.0;
        }

        String value = phenomenon.toLowerCase();

        if (value.contains("glaze") || value.contains("hail") || value.contains("thunder")) {
            throw new RuntimeException("Usage of selected vehicle type is forbidden");
        }

        if (value.contains("snow") || value.contains("sleet")) {
            return 1.0;
        }

        if (value.contains("rain")) {
            return 0.5;
        }

        return 0.0;
    }
}