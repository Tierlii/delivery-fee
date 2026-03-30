package com.fujitsu.deliveryfee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI deliveryFeeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Delivery Fee API")
                        .description("API for calculating delivery fees based on city, vehicle type, and weather conditions")
                        .version("1.0"));
    }
}