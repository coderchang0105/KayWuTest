package com.coderchang.kaywuzhihu.domain;

import java.util.List;

/**
 * Created by coderchang on 2016/6/5.
 */
public class Weather {
    private String cityName;
    private String cond;
    private String tmp;
    private String tmpMax;
    private String tmpMin;
    private String pm;
    private String airCondition;


    private List<WeatherHourlyForecast> hourlyForecastList;
    private List<WeatherDailyForecast> dailyForecastList;
    private WeatherSuggestion suggestion;

    public Weather(String cond, String tmp, String tmpMax, String tmpMin, String pm,
                   String airCondition, List<WeatherHourlyForecast> hourlyForecastList,
                   List<WeatherDailyForecast> dailyForecastList, WeatherSuggestion suggestion,String cityName) {
        this.cond = cond;
        this.tmp = tmp;
        this.tmpMax = tmpMax;
        this.tmpMin = tmpMin;
        this.pm = pm;
        this.airCondition = airCondition;
        this.hourlyForecastList = hourlyForecastList;
        this.dailyForecastList = dailyForecastList;
        this.suggestion = suggestion;
        this.cityName = cityName;
    }

    public Weather() {
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getTmpMax() {
        return tmpMax;
    }

    public void setTmpMax(String tmpMax) {
        this.tmpMax = tmpMax;
    }

    public String getTmpMin() {
        return tmpMin;
    }

    public void setTmpMin(String tmpMin) {
        this.tmpMin = tmpMin;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public List<WeatherHourlyForecast> getHourlyForecastList() {
        return hourlyForecastList;
    }

    public void setHourlyForecastList(List<WeatherHourlyForecast> hourlyForecastList) {
        this.hourlyForecastList = hourlyForecastList;
    }

    public List<WeatherDailyForecast> getDailyForecastList() {
        return dailyForecastList;
    }

    public void setDailyForecastList(List<WeatherDailyForecast> dailyForecastList) {
        this.dailyForecastList = dailyForecastList;
    }

    public WeatherSuggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(WeatherSuggestion suggestion) {
        this.suggestion = suggestion;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "cond='" + cond + '\'' +
                ", tmp='" + tmp + '\'' +
                ", tmpMax='" + tmpMax + '\'' +
                ", tmpMin='" + tmpMin + '\'' +
                ", pm='" + pm + '\'' +
                ", airCondition='" + airCondition + '\'' +
                ", hourlyForecastList=" + hourlyForecastList.toString() +
                ", dailyForecastList=" + dailyForecastList.toString() +
                ", suggestion=" + suggestion.toString() +
                '}';
    }

}
