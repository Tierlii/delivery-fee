package com.fujitsu.deliveryfee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fujitsu.deliveryfee.domain.entity.WeatherObservation;
import com.fujitsu.deliveryfee.domain.enums.City;
import com.fujitsu.deliveryfee.domain.model.ParsedWeatherObservation;
import com.fujitsu.deliveryfee.integration.StationToCityMapper;
import com.fujitsu.deliveryfee.integration.WeatherApiClient;
import com.fujitsu.deliveryfee.integration.WeatherXmlParser;
import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;

@Service
public class WeatherImportService {

    private final WeatherApiClient weatherApiClient;
    private final WeatherXmlParser weatherXmlParser;
    private final StationToCityMapper stationToCityMapper;
    private final WeatherObservationRepository weatherObservationRepository;

    public WeatherImportService(WeatherApiClient weatherApiClient,
                                WeatherXmlParser weatherXmlParser,
                                StationToCityMapper stationToCityMapper,
                                WeatherObservationRepository weatherObservationRepository) {
        this.weatherApiClient = weatherApiClient;
        this.weatherXmlParser = weatherXmlParser;
        this.stationToCityMapper = stationToCityMapper;
        this.weatherObservationRepository = weatherObservationRepository;
    }

    @Transactional
    public void importWeatherData() {
        String xml = weatherApiClient.fetchWeatherData();
        List<ParsedWeatherObservation> parsedObservations = weatherXmlParser.parse(xml);

        for (ParsedWeatherObservation parsed : parsedObservations) {
            var cityOptional = stationToCityMapper.mapToCity(parsed.getStationName());

            if (cityOptional.isEmpty()) {
                continue;
            }

            City city = cityOptional.get();

            WeatherObservation observation = new WeatherObservation();
            observation.setStationName(parsed.getStationName());
            observation.setWmoCode(parsed.getWmoCode());
            observation.setAirTemperature(parsed.getAirTemperature());
            observation.setWindSpeed(parsed.getWindSpeed());
            observation.setWeatherPhenomenon(parsed.getWeatherPhenomenon());
            observation.setObservationTime(parsed.getObservationTime());
            observation.setCity(city);

            weatherObservationRepository.save(observation);
        }
    }
}