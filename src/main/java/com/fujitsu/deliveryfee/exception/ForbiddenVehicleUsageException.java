package com.fujitsu.deliveryfee.exception;

public class ForbiddenVehicleUsageException extends RuntimeException {

    public ForbiddenVehicleUsageException(String message) {
        super(message);
    }
}