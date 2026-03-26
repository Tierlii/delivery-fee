package com.fujitsu.deliveryfee.domain.dto;

import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

public class DeliveryFeeRequest {

    private City city;
    private VehicleType vehicleType;

    public DeliveryFeeRequest() {}

    public DeliveryFeeRequest(City city, VehicleType vehicleType) {
        this.city = city;
        this.vehicleType = vehicleType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}