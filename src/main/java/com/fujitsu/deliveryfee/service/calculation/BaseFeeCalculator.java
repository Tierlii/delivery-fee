package com.fujitsu.deliveryfee.service.calculation;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

@Component
public class BaseFeeCalculator {

    public double calculateBaseFee(City city, VehicleType vehicleType) {

        switch (city) {

            case TALLINN:
                return calculateTallinnFee(vehicleType);

            case TARTU:
                return calculateTartuFee(vehicleType);

            case PARNU:
                return calculateParnuFee(vehicleType);

            default:
                throw new RuntimeException("Unsupported city: " + city);
        }
    }

    private double calculateTallinnFee(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 4.0;
            case SCOOTER:
                return 3.5;
            case BIKE:
                return 3.0;
            default:
                throw new RuntimeException("Unsupported vehicle type: " + vehicleType);
        }
    }

    private double calculateTartuFee(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 3.5;
            case SCOOTER:
                return 3.0;
            case BIKE:
                return 2.5;
            default:
                throw new RuntimeException("Unsupported vehicle type: " + vehicleType);
        }
    }

    private double calculateParnuFee(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return 3.0;
            case SCOOTER:
                return 2.5;
            case BIKE:
                return 2.0;
            default:
                throw new RuntimeException("Unsupported vehicle type: " + vehicleType);
        }
    }
}