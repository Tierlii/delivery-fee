package com.fujitsu.deliveryfee.repository;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherObservationRepository extends JpaRepository<WeatherObservation, Long> {

    Optional<WeatherObservation> findFirstByStationNameOrderByObservationTimeDesc(String stationName);

}