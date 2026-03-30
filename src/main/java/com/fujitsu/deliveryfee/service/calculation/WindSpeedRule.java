package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.exception.ForbiddenVehicleUsageException;

@Component
public class WindSpeedRule implements ExtraFeeRule {

    @Override
    public double calculate(double airTemperature,
                            double windSpeed,
                            String weatherPhenomenon,
                            VehicleType vehicleType) {

        if (vehicleType != VehicleType.BIKE) {
            return 0.0;
        }

        if (windSpeed > 20) {
            throw new ForbiddenVehicleUsageException("Usage of selected vehicle type is forbidden");
        }

        if (windSpeed >= 10 && windSpeed <= 20) {
            return 0.5;
        }

        return 0.0;
    }
}