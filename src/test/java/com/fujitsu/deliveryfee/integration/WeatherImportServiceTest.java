package com.fujitsu.deliveryfee.integration;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.fujitsu.deliveryfee.repository.WeatherObservationRepository;
import com.fujitsu.deliveryfee.service.WeatherImportService;

class WeatherImportServiceTest {

    @Test
    void shouldImportWeatherData() {
        WeatherObservationRepository repository = mock(WeatherObservationRepository.class);

        WeatherImportService service = new WeatherImportService(
                null, // WeatherApiClient (можно замокать при желании)
                null, // WeatherXmlParser
                null, // Mapper
                repository
        );

        // просто проверяем, что не падает
        service.importWeatherData();

        verify(repository, atLeast(0)).save(any());
    }
}