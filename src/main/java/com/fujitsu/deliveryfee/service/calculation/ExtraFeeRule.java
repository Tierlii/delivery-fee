package com.fujitsu.deliveryfee.service.calculation;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;

public interface ExtraFeeRule {

    double calculate(
            double airTemperature,
            double windSpeed,
            String weatherPhenomenon,
            VehicleType vehicleType
    );
}