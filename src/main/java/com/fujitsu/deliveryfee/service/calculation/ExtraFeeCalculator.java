package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;

@Component
public class ExtraFeeCalculator {

    private final AirTemperatureRule airTemperatureRule;
    private final WindSpeedRule windSpeedRule;
    private final WeatherPhenomenonRule weatherPhenomenonRule;

    public ExtraFeeCalculator(AirTemperatureRule airTemperatureRule,
                              WindSpeedRule windSpeedRule,
                              WeatherPhenomenonRule weatherPhenomenonRule) {
        this.airTemperatureRule = airTemperatureRule;
        this.windSpeedRule = windSpeedRule;
        this.weatherPhenomenonRule = weatherPhenomenonRule;
    }

    public double calculate(double airTemperature,
                            double windSpeed,
                            String weatherPhenomenon,
                            VehicleType vehicleType) {

        return airTemperatureRule.calculate(airTemperature, windSpeed, weatherPhenomenon, vehicleType)
                + windSpeedRule.calculate(airTemperature, windSpeed, weatherPhenomenon, vehicleType)
                + weatherPhenomenonRule.calculate(airTemperature, windSpeed, weatherPhenomenon, vehicleType);
    }
}