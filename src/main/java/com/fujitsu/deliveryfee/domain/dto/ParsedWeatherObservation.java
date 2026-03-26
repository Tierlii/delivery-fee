package com.fujitsu.deliveryfee.domain.dto;

public class ParsedWeatherObservation {

    private String stationName;
    private double airTemperature;
    private double windSpeed;
    private String weatherPhenomenon;

    public ParsedWeatherObservation() {}

    public ParsedWeatherObservation(String stationName,
                                    double airTemperature,
                                    double windSpeed,
                                    String weatherPhenomenon) {
        this.stationName = stationName;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.weatherPhenomenon = weatherPhenomenon;
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
}