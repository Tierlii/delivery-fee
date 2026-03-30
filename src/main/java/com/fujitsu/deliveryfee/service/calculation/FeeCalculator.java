package com.fujitsu.deliveryfee.service.calculation;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class FeeCalculator {

    private final BaseFeeCalculator baseFeeCalculator;
    private final ExtraFeeCalculator extraFeeCalculator;

    public FeeCalculator(BaseFeeCalculator baseFeeCalculator,
                         ExtraFeeCalculator extraFeeCalculator) {
        this.baseFeeCalculator = baseFeeCalculator;
        this.extraFeeCalculator = extraFeeCalculator;
    }

    public double calculate(City city,
                            VehicleType vehicleType,
                            double airTemperature,
                            double windSpeed,
                            String weatherPhenomenon) {

        double baseFee = baseFeeCalculator.calculate(city, vehicleType);
        double extraFee = extraFeeCalculator.calculate(
                airTemperature,
                windSpeed,
                weatherPhenomenon,
                vehicleType
        );

        return baseFee + extraFee;
    }
}