package com.fujitsu.deliveryfee.service;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryFeeService {

    private final WeatherDataService weatherDataService;

    public DeliveryFeeResponse calculateFee(City city, VehicleType vehicleType) {

        // 1. Получаем последнюю погоду
        WeatherObservation weather = weatherDataService.getLatestWeatherForCity(city);

        // 2. Пока заглушка (реальную логику добавим в следующих коммитах)
        double totalFee = 0.0;

        return DeliveryFeeResponse.builder()
                .city(city)
                .vehicleType(vehicleType)
                .fee(totalFee)
                .build();
    }
}