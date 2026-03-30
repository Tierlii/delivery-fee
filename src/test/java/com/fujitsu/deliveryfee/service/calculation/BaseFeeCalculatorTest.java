package com.fujitsu.deliveryfee.service.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

class BaseFeeCalculatorTest {

    private final BaseFeeCalculator calculator = new BaseFeeCalculator();

    @Test
    void shouldReturnCorrectFeeForTallinnCar() {
        double fee = calculator.calculate(City.TALLINN, VehicleType.CAR);
        assertEquals(4.0, fee);
    }

    @Test
    void shouldReturnCorrectFeeForTartuBike() {
        double fee = calculator.calculate(City.TARTU, VehicleType.BIKE);
        assertEquals(2.5, fee);
    }

    @Test
    void shouldReturnCorrectFeeForParnuScooter() {
        double fee = calculator.calculate(City.PARNU, VehicleType.SCOOTER);
        assertEquals(2.5, fee);
    }
}