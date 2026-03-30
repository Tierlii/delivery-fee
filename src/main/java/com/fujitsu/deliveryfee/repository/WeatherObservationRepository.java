package com.fujitsu.deliveryfee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;

public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    Optional<WeatherObservation> findFirstByStationNameOrderByObservationTimeDesc(String stationName);

    Optional<WeatherObservation> findTopByCityOrderByObservationTimeDesc(City city);

}