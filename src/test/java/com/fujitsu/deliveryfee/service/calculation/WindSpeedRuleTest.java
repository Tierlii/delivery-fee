package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.exception.ForbiddenVehicleUsageException;

class WindSpeedRuleTest {

    private final WindSpeedRule rule = new WindSpeedRule();

    @Test
    void shouldAddHalfEuroForBikeWhenWindBetween10And20() {
        double fee = rule.calculate(0.0, 15.0, "", VehicleType.BIKE);
        assertEquals(0.5, fee);
    }

    @Test
    void shouldThrowExceptionWhenWindAbove20() {
        assertThrows(ForbiddenVehicleUsageException.class,
                () -> rule.calculate(0.0, 25.0, "", VehicleType.BIKE));
    }

    @Test
    void shouldReturnZeroForCar() {
        double fee = rule.calculate(0.0, 30.0, "", VehicleType.CAR);
        assertEquals(0.0, fee);
    }
}