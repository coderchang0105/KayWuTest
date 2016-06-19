package com.coderchang.kaywuzhihu.domain;

/**
 * Created by coderchang on 2016/6/5.
 */
public class WeatherSuggestion {
    private String cloth;
    private String clothSuggestion;

    private String sport;
    private String sportSuggestion;

    private String travel;
    private String travelSuggestion;

    private String flu;
    private String fluSuggestion;

    public WeatherSuggestion(String cloth, String clothSuggestion, String sport, String sportSuggestion,
                             String travel, String travelSuggestion, String flu, String fluSuggestion) {
        this.cloth = cloth;
        this.clothSuggestion = clothSuggestion;
        this.sport = sport;
        this.sportSuggestion = sportSuggestion;
        this.travel = travel;
        this.travelSuggestion = travelSuggestion;
        this.flu = flu;
        this.fluSuggestion = fluSuggestion;
    }

    public WeatherSuggestion() {
    }

    public String getCloth() {
        return cloth;
    }

    public void setCloth(String cloth) {
        this.cloth = cloth;
    }

    public String getClothSuggestion() {
        return clothSuggestion;
    }

    public void setClothSuggestion(String clothSuggestion) {
        this.clothSuggestion = clothSuggestion;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getSportSuggestion() {
        return sportSuggestion;
    }

    public void setSportSuggestion(String sportSuggestion) {
        this.sportSuggestion = sportSuggestion;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getTravelSuggestion() {
        return travelSuggestion;
    }

    public void setTravelSuggestion(String travelSuggestion) {
        this.travelSuggestion = travelSuggestion;
    }

    public String getFlu() {
        return flu;
    }

    public void setFlu(String flu) {
        this.flu = flu;
    }

    public String getFluSuggestion() {
        return fluSuggestion;
    }

    public void setFluSuggestion(String fluSuggestion) {
        this.fluSuggestion = fluSuggestion;
    }


    @Override
    public String toString() {
        return "WeatherSuggestion{" +
                "cloth='" + cloth + '\'' +
                ", clothSuggestion='" + clothSuggestion + '\'' +
                ", sport='" + sport + '\'' +
                ", sportSuggestion='" + sportSuggestion + '\'' +
                ", travel='" + travel + '\'' +
                ", travelSuggestion='" + travelSuggestion + '\'' +
                ", flu='" + flu + '\'' +
                ", fluSuggestion='" + fluSuggestion + '\'' +
                '}';
    }
}
