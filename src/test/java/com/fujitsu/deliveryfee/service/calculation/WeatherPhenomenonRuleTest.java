package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.exception.ForbiddenVehicleUsageException;

class WeatherPhenomenonRuleTest {

    private final WeatherPhenomenonRule rule = new WeatherPhenomenonRule();

    @Test
    void shouldAddOneEuroForSnow() {
        double fee = rule.calculate(0.0, 0.0, "Light snow", VehicleType.BIKE);
        assertEquals(1.0, fee);
    }

    @Test
    void shouldAddHalfEuroForRain() {
        double fee = rule.calculate(0.0, 0.0, "Moderate rain", VehicleType.SCOOTER);
        assertEquals(0.5, fee);
    }

    @Test
    void shouldThrowExceptionForGlaze() {
        assertThrows(ForbiddenVehicleUsageException.class,
                () -> rule.calculate(0.0, 0.0, "Glaze", VehicleType.BIKE));
    }

    @Test
    void shouldReturnZeroForClearWeather() {
        double fee = rule.calculate(0.0, 0.0, "Clear", VehicleType.BIKE);
        assertEquals(0.0, fee);
    }
}