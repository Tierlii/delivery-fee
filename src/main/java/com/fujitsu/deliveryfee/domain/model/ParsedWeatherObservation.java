package com.fujitsu.deliveryfee.domain.model;

import java.time.LocalDateTime;

public class ParsedWeatherObservation {

    private String stationName;
    private String wmoCode;
    private Double airTemperature;
    private Double windSpeed;
    private String weatherPhenomenon;
    private LocalDateTime observationTime;

    public ParsedWeatherObservation() {}

    public ParsedWeatherObservation(String stationName,
                                    String wmoCode,
                                    Double airTemperature,
                                    Double windSpeed,
                                    String weatherPhenomenon,
                                    LocalDateTime observationTime) {
        this.stationName = stationName;
        this.wmoCode = wmoCode;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.weatherPhenomenon = weatherPhenomenon;
        this.observationTime = observationTime;
    }

    public String getStationName() {
        return stationName;
    }

    public String getWmoCode() {
        return wmoCode;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getWeatherPhenomenon() {
        return weatherPhenomenon;
    }

    public LocalDateTime getObservationTime() {
        return observationTime;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setWmoCode(String wmoCode) {
        this.wmoCode = wmoCode;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWeatherPhenomenon(String weatherPhenomenon) {
        this.weatherPhenomenon = weatherPhenomenon;
    }

    public void setObservationTime(LocalDateTime observationTime) {
        this.observationTime = observationTime;
    }

    @Override
    public String toString() {
        return "ParsedWeatherObservation{" +
                "stationName='" + stationName + '\'' +
                ", wmoCode='" + wmoCode + '\'' +
                ", airTemperature=" + airTemperature +
                ", windSpeed=" + windSpeed +
                ", weatherPhenomenon='" + weatherPhenomenon + '\'' +
                ", observationTime=" + observationTime +
                '}';
    }
}