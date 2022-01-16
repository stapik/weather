package org.example.weatherHistory;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "weather_history")
public class WeatherHistoryEntity {

    @Column(unique = true, nullable = false)
    private Date weatherDate;
    @Column
    private String weatherValue;

    public void setWeatherDate(Date date) {
        this.weatherDate = date;
    }

    @Id
    public Date getWeatherDate() {
        return weatherDate;
    }

    public String getWeatherValue() {
        return weatherValue;
    }

    public void setWeatherValue(String weather_value) {
        this.weatherValue = weather_value;
    }
}
