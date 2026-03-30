package com.fujitsu.deliveryfee.controller;

import com.fujitsu.deliveryfee.domain.dto.DeliveryFeeResponse;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.enums.VehicleType;
import com.fujitsu.deliveryfee.service.DeliveryFeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryFeeController.class)
class DeliveryFeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryFeeService service;

    @Test
    void shouldReturnDeliveryFee() throws Exception {
        DeliveryFeeResponse response = DeliveryFeeResponse.builder()
                .city(City.TARTU)
                .vehicleType(VehicleType.BIKE)
                .fee(4.5)
                .build();

        when(service.calculateFee(City.TARTU, VehicleType.BIKE))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/delivery-fee")
                        .param("city", "TARTU")
                        .param("vehicleType", "BIKE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fee").value(4.5));
    }
}