package com.fujitsu.deliveryfee.domain.dto;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryFeeResponse {

    private City city;
    private VehicleType vehicleType;
    private double fee;
}