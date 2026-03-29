package com.fujitsu.deliveryfee.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fujitsu.deliveryfee.service.WeatherImportService;

@Component
public class WeatherImportScheduler {

    private final WeatherImportService weatherImportService;

    public WeatherImportScheduler(WeatherImportService weatherImportService) {
        this.weatherImportService = weatherImportService;
    }

    @Scheduled(cron = "${weather.import.cron}")
    public void importWeatherData() {
        System.out.println("SCHEDULER STARTED: Importing weather data...");
        weatherImportService.importWeatherData();
        System.out.println("SCHEDULER FINISHED");
    }
}