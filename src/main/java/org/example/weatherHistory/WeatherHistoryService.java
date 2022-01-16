package org.example.weatherHistory;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

@Service
public class WeatherHistoryService {

    WeatherHistoryRepository repository;

    WeatherHistoryService(WeatherHistoryRepository repository) {
        this.repository = repository;
    }

    public WeatherHistoryEntity getCurrentWeather() {
        Optional<WeatherHistoryEntity> entity = repository.findByWeatherDate(getCurrentDate());
        return entity.orElseGet(this::saveAndGetCurrentWeather);
    }

    private WeatherHistoryEntity saveAndGetCurrentWeather() {
        String temperature = parseTemperature();
        if (temperature == null) {
            throw new RuntimeException("Parsing error. Temperature not found.");
        }
        WeatherHistoryEntity entity = new WeatherHistoryEntity();
        entity.setWeatherDate(getCurrentDate());
        entity.setWeatherValue(temperature);
        repository.save(entity);
        return entity;
    }

    private String parseTemperature() {
        try {
            URL url = new URL("https://yandex.ru");
            Scanner sc = new Scanner(url.openStream());
            Stream<MatchResult> matches = sc.findAll("<div class='weather__temp'>([^<]*)");
            MatchResult matchResult = matches.findFirst().orElseThrow();
            return matchResult.group(1) + "C";
        } catch (NoSuchElementException | IOException exception) {
            return null;
        }
    }

    private Date getCurrentDate() {
        LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Europe/Moscow"));
        return Date.valueOf(todayLocalDate);
    }
}
