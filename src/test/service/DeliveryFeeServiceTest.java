package com.fujitsu.deliveryfee.service;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.service.calculation.FeeCalculator;

@ExtendWith(MockitoExtension.class)
class DeliveryFeeServiceTest {

    @Mock
    private WeatherDataService weatherDataService;

    @Mock
    private FeeCalculator feeCalculator;

    @InjectMocks
    private DeliveryFeeService service;

    @Test
    void shouldCalculateDeliveryFee() {
        WeatherObservation weather = new WeatherObservation();
        weather.setCity(City.TARTU);
        weather.setAirTemperature(-5.0);
        weather.setWindSpeed(10.0);
        weather.setWeatherPhenomenon("Light snow");
        weather.setObservationTime(LocalDateTime.now());

        when(weatherDataService.getLatestWeatherForCity(City.TARTU))
                .thenReturn(weather);

        when(feeCalculator.calculate(
                eq(City.TARTU),
                eq(VehicleType.BIKE),
                anyDouble(),
                anyDouble(),
                anyString()
        )).thenReturn(4.5);

        DeliveryFeeResponse response =
                service.calculateFee(City.TARTU, VehicleType.BIKE);

        assertNotNull(response);
        assertEquals(4.5, response.getFee());
    }
}