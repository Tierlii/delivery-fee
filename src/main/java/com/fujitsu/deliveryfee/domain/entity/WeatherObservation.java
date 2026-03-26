package com.fujitsu.deliveryfee.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_observation")
public class WeatherObservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stationName;

    private double airTemperature;

    private double windSpeed;

    private String weatherPhenomenon;

    private LocalDateTime observationTime;

    public WeatherObservation() {}

    public WeatherObservation(String stationName,
                              double airTemperature,
                              double windSpeed,
                              String weatherPhenomenon,
                              LocalDateTime observationTime) {
        this.stationName = stationName;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.weatherPhenomenon = weatherPhenomenon;
        this.observationTime = observationTime;
    }

    public Long getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherPhenomenon() {
        return weatherPhenomenon;
    }

    public void setWeatherPhenomenon(String weatherPhenomenon) {
        this.weatherPhenomenon = weatherPhenomenon;
    }

    public LocalDateTime getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(LocalDateTime observationTime) {
        this.observationTime = observationTime;
    }
}