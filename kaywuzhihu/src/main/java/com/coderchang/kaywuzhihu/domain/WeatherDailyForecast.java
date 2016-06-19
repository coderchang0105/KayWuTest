package com.coderchang.kaywuzhihu.domain;

import android.widget.ImageView;

/**
 * Created by coderchang on 2016/6/5.
 */
public class WeatherDailyForecast {
    private String codeD;//天气情况1
    private String codeN;//天气情况2
    private String date;//日期
    private String tmpMax;
    private String tmpMin;
    private Wind wind;

    private String textD;//天气1tex
    private String textN;//天气2tex

    public WeatherDailyForecast(String codeD, String codeN, String date, String tmpMax, String tmpMin,Wind wind,String textD,String textN) {
        this.codeD = codeD;
        this.codeN = codeN;
        this.date = date;
        this.tmpMax = tmpMax;
        this.tmpMin = tmpMin;
        this.wind = wind;
        this.textD = textD;
        this.textN = textN;
    }


    public String getCodeD() {
        return codeD;
    }

    public void setCodeD(String codeD) {
        this.codeD = codeD;
    }

    public String getCodeN() {
        return codeN;
    }

    public void setCodeN(String codeN) {
        this.codeN = codeN;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Wind getWind() {
        return wind;
    }

    public WeatherDailyForecast() {
    }

    public String getTextD() {
        return textD;
    }

    public void setTextD(String textD) {
        this.textD = textD;
    }

    public String getTextN() {
        return textN;
    }

    public void setTextN(String textN) {
        this.textN = textN;
    }


    @Override
    public String toString() {
        return "WeatherDailyForecast{" +
                "codeD='" + codeD + '\'' +
                ", codeN='" + codeN + '\'' +
                ", date='" + date + '\'' +
                ", tmpMax='" + tmpMax + '\'' +
                ", tmpMin='" + tmpMin + '\'' +
                ", wind=" + wind +
                ", textD='" + textD + '\'' +
                ", textN='" + textN + '\'' +
                '}';
    }
}
