package com.fujitsu.deliveryfee.dto;

public class DeliveryFeeResponse {

    private double fee;

    public DeliveryFeeResponse() {}

    public DeliveryFeeResponse(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}