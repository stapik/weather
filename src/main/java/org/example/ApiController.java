package org.example;

import org.example.weatherHistory.WeatherHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final WeatherHistoryService weatherService;

    ApiController(WeatherHistoryService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String weather() {
        return weatherService.getCurrentWeather().getWeatherValue();
    }
}
