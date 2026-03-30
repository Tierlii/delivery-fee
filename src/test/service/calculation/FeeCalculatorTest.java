package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

class FeeCalculatorTest {

    private final FeeCalculator calculator = new FeeCalculator(
            new BaseFeeCalculator(),
            new ExtraFeeCalculator(
                    new AirTemperatureRule(),
                    new WindSpeedRule(),
                    new WeatherPhenomenonRule()
            )
    );

    @Test
    void shouldCalculateTotalFee() {
        double fee = calculator.calculate(
                City.TARTU,
                VehicleType.BIKE,
                -5.0,
                12.0,
                "Light snow"
        );

        assertEquals(4.5, fee);
    }
}