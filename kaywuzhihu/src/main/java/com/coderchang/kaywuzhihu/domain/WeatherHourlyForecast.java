package com.coderchang.kaywuzhihu.domain;

/**
 * Created by coderchang on 2016/6/5.
 */
public class WeatherHourlyForecast {
    private String time;
    private String tmp;
    private String humidity;//湿度
    private Wind wind;

    public WeatherHourlyForecast(String time, String tmp, String humidity, Wind wind) {
        this.time = time;
        this.tmp = tmp;
        this.humidity = humidity;
        this.wind = wind;
    }

    public WeatherHourlyForecast() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
