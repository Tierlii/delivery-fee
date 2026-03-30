package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;

class ExtraFeeCalculatorTest {

    private final ExtraFeeCalculator calculator = new ExtraFeeCalculator(
            new AirTemperatureRule(),
            new WindSpeedRule(),
            new WeatherPhenomenonRule()
    );

    @Test
    void shouldCalculateTotalExtraFee() {
        double fee = calculator.calculate(-5.0, 12.0, "Light snow", VehicleType.BIKE);
        assertEquals(2.0, fee);
    }

    @Test
    void shouldReturnZeroWhenNoConditionsMatch() {
        double fee = calculator.calculate(10.0, 3.0, "Clear", VehicleType.CAR);
        assertEquals(0.0, fee);
    }
}