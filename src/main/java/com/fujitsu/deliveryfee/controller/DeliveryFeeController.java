package com.fujitsu.deliveryfee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.exception.InvalidParameterException;
import com.fujitsu.deliveryfee.service.DeliveryFeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/delivery-fee")
@RequiredArgsConstructor
public class DeliveryFeeController {

    private final DeliveryFeeService deliveryFeeService;

    @GetMapping
    public DeliveryFeeResponse calculateDeliveryFee(
            @RequestParam String city,
            @RequestParam String vehicleType
    ) {
        try {
            City parsedCity = City.valueOf(city.toUpperCase());
            VehicleType parsedVehicleType = VehicleType.valueOf(vehicleType.toUpperCase());

            return deliveryFeeService.calculateFee(parsedCity, parsedVehicleType);

        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException("Invalid city or vehicle type");
        }
    }
}