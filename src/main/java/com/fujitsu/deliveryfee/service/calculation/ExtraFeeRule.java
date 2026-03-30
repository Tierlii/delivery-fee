package com.fujitsu.deliveryfee.service.calculation;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;

public interface ExtraFeeRule {

    double calculateExtraFee(VehicleType vehicleType, WeatherObservation weatherObservation);
}