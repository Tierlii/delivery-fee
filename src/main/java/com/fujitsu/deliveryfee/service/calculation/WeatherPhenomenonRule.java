package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.exception.ForbiddenVehicleUsageException;

@Component
public class WeatherPhenomenonRule implements ExtraFeeRule {

    @Override
    public double calculate(double airTemperature,
                            double windSpeed,
                            String weatherPhenomenon,
                            VehicleType vehicleType) {

        if (vehicleType == VehicleType.CAR) {
            return 0.0;
        }

        if (weatherPhenomenon == null || weatherPhenomenon.isBlank()) {
            return 0.0;
        }

        String phenomenon = weatherPhenomenon.toLowerCase();

        if (phenomenon.contains("glaze")
                || phenomenon.contains("hail")
                || phenomenon.contains("thunder")) {
            throw new ForbiddenVehicleUsageException("Usage of selected vehicle type is forbidden");
        }

        if (phenomenon.contains("snow") || phenomenon.contains("sleet")) {
            return 1.0;
        }

        if (phenomenon.contains("rain")) {
            return 0.5;
        }

        return 0.0;
    }
}