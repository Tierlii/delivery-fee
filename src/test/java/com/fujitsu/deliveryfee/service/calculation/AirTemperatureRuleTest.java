package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;

class AirTemperatureRuleTest {

    private final AirTemperatureRule rule = new AirTemperatureRule();

    @Test
    void shouldAddOneEuroWhenTempBelowMinus10() {
        double fee = rule.calculate(-15.0, 0.0, "", VehicleType.BIKE);
        assertEquals(1.0, fee);
    }

    @Test
    void shouldAddHalfEuroWhenTempBetweenMinus10AndZero() {
        double fee = rule.calculate(-5.0, 0.0, "", VehicleType.SCOOTER);
        assertEquals(0.5, fee);
    }

    @Test
    void shouldAddZeroWhenTempAboveZero() {
        double fee = rule.calculate(5.0, 0.0, "", VehicleType.BIKE);
        assertEquals(0.0, fee);
    }

    @Test
    void shouldReturnZeroForCar() {
        double fee = rule.calculate(-20.0, 0.0, "", VehicleType.CAR);
        assertEquals(0.0, fee);
    }
}