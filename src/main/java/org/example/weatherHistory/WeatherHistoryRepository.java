package org.example.weatherHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface  WeatherHistoryRepository extends JpaRepository<WeatherHistoryEntity, Date> {

    Optional<WeatherHistoryEntity> findByWeatherDate(Date date);
}
