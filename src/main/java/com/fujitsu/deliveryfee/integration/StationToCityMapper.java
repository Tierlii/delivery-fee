package com.fujitsu.deliveryfee.integration;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.domain.enums.City;

@Component
public class StationToCityMapper {

    private static final Map<String, City> STATION_TO_CITY_MAP = Map.of(
            "Tallinn-Harku", City.TALLINN,
            "Tartu-Tõravere", City.TARTU,
            "Pärnu", City.PARNU
    );

    public Optional<City> mapToCity(String stationName) {
        if (stationName == null) {
            return Optional.empty();
        }

        String normalized = stationName.trim().toLowerCase();

        if (normalized.contains("tallinn-harku")) {
            return Optional.of(City.TALLINN);
        }

        if (normalized.contains("tartu")) {
            return Optional.of(City.TARTU);
        }

        if (normalized.contains("parnu")) {
            return Optional.of(City.PARNU);
        }

        return Optional.empty();
    }
}