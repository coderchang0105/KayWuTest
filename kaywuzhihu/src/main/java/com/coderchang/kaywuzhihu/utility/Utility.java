package com.coderchang.kaywuzhihu.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.domain.News;
import com.coderchang.kaywuzhihu.domain.Weather;
import com.coderchang.kaywuzhihu.domain.WeatherDailyForecast;
import com.coderchang.kaywuzhihu.domain.WeatherHourlyForecast;
import com.coderchang.kaywuzhihu.domain.WeatherSuggestion;
import com.coderchang.kaywuzhihu.domain.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by coderchang on 2016/5/27.
 */
public class Utility {

    /**
     * 年月日的时间
     *
     * @return
     */
    public static String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
        return format.format(c.getTime());
    }

    /**
     * 解析jason to newsList
     *
     * @return
     */
    public static List<News> parseJsonToNewsList(String newsStr) {
        List<News> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(newsStr);
            String date = jsonObject.getString("date");
            JSONArray stories = jsonObject.getJSONArray("stories");

            for (int i = 0; i < stories.length(); i++) {
                News news = new News();
                JSONObject story = new JSONObject();
                story = stories.getJSONObject(i);
                JSONArray images = story.getJSONArray("images");
                news.setId(story.getInt("id"));
                news.setTitle(story.getString("title"));
                news.setImage(images.getString(0));
                newsList.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public static boolean NetWorkIsConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;

    }

    /**
     * 通过返回的cond代码得到对应天气的图片 如阴天、晴天、下雨天等等
     */
    public static int getCondResource(String code) {
        int resourceCode;
        switch (Integer.parseInt(code)) {
            case 100:
                return R.drawable.sunny;
            case 101:
                return R.drawable.cloudy;
            case 102:
                return R.drawable.few_cloud;
            case 103:
                return R.drawable.partly_cloudy;
            case 104:
                return R.drawable.overcast;
            case 200:
                return R.drawable.windy;
            case 201:
                return R.drawable.calm;
            case 202:
                return R.drawable.light_breeze;
            case 203:
                return R.drawable.moderate_breeze;
            case 204:
                return R.drawable.fresh_breeze;
            case 205:
                return R.drawable.strong_breeze;
            case 206:
                return R.drawable.high_wind;
            case 207:
                return R.drawable.gale;
            case 208:
                return R.drawable.strong_gale;
            case 209:
                return R.drawable.storm;
            case 210:
                return R.drawable.violent_storm;
            case 211:
                return R.drawable.hurricane;
            case 212:
                return R.drawable.tornado;
            case 213:
                return R.drawable.tropical_storm;
            case 300:
                return R.drawable.shower_rain;
            case 301:
                return R.drawable.heavy_shower_rain;
            case 302:
                return R.drawable.thunder_shower;
            case 303:
                return R.drawable.heavy_thunder_storm;
            case 304:
                return R.drawable.hail;
            case 305:
                return R.drawable.light_rain;
            case 306:
                return R.drawable.moderate_rain;
            case 307:
                return R.drawable.heavy_rain;
            case 308:
                return R.drawable.extreme_rain;
            case 309:
                return R.drawable.drizzle_rain;
            case 310:
                return R.drawable.storm_rain;
            case 311:
                return R.drawable.heavy_storm_rain;
            case 312:
                return R.drawable.severe_storm_rain;
            case 313:
                return R.drawable.freezing_rain;
            case 400:
                return R.drawable.light_snow;
            case 401:
                return R.drawable.moderate_snow;
            case 402:
                return R.drawable.heavy_snow;
            case 403:
                return R.drawable.snow_storm;
            case 404:
                return R.drawable.sleet;
            case 405:
                return R.drawable.rain_and_snow;
            case 406:
                return R.drawable.shower_snow;
            case 407:
                return R.drawable.snow_flurry;
            case 500:
                return R.drawable.mist;
            case 501:
                return R.drawable.foggy;
            case 502:
                return R.drawable.haze;
            case 503:
                return R.drawable.sand;
            case 504:
                return R.drawable.dust;
            case 507:
                return R.drawable.dust_storm;
            case 508:
                return R.drawable.sand_storm;
            case 999:
                return R.drawable.unknow;

        }
        return R.drawable.unknow;
    }

    public static String getWeatherDetail(WeatherDailyForecast forecast) {
        if (forecast.getTextD().equals(forecast.getTextN())) {
            return forecast.getTextD()+ "最高" + forecast.getTmpMax()
                    + "℃。" + forecast.getWind().getSc() + "级" + forecast.getWind().getDir() + forecast.getWind().getSpeed() +
                    "km/h。";
        }
        return forecast.getTextD() + "转" + forecast.getTextN() + "最高" + forecast.getTmpMax()
                + "℃。" + forecast.getWind().getSc() + "级" + forecast.getWind().getDir() + forecast.getWind().getSpeed() +
                "km/h。";
    }

    public static String subCityName(String longCityName) {
        return longCityName.substring(0, longCityName.length() - 1);
    }

    public static Weather parseJsonToWeather(String weatherString) {
        Weather weather = new Weather();

        try {
            JSONObject weatherData = new JSONObject(weatherString);
            JSONArray weatherArray = weatherData.getJSONArray("HeWeather data service 3.0");
            JSONObject fullWeather = weatherArray.getJSONObject(0);
            JSONObject aqi = fullWeather.getJSONObject("aqi");
            JSONObject city = aqi.getJSONObject("city");
            weather.setPm(city.getString("pm25"));//设置pm25
            if (city.getString("qlty") == null) {
                if (Integer.parseInt(city.getString("pm25")) <= 50) {
                    weather.setAirCondition("优");
                }
                if (Integer.parseInt(city.getString("pm25")) > 50 && Integer.parseInt(city.getString("pm25")) <= 100){
                    weather.setAirCondition("良");
                }
                if (Integer.parseInt(city.getString("pm25")) > 100 && Integer.parseInt(city.getString("pm25")) <= 200){
                    weather.setAirCondition("轻度污染");
                }
                if (Integer.parseInt(city.getString("pm25")) > 200 && Integer.parseInt(city.getString("pm25")) <= 300){
                    weather.setAirCondition("中度污染");
                }
                if (Integer.parseInt(city.getString("pm25")) > 300) {
                    weather.setAirCondition("重度污染");
                }
            }else {
                weather.setAirCondition(city.getString("qlty"));
            }

            JSONObject basic = fullWeather.getJSONObject("basic");
            weather.setCityName(basic.getString("city"));//设置cityName
            JSONArray dailyForecast = fullWeather.getJSONArray("daily_forecast");
            List<WeatherDailyForecast> weatherDailyForecastList = new ArrayList<>();
            for (int i = 0; i < dailyForecast.length(); i++) {
                WeatherDailyForecast weatherDailyForecast = new WeatherDailyForecast();
                JSONObject object = dailyForecast.getJSONObject(i);
                JSONObject cond = object.getJSONObject("cond");
                weatherDailyForecast.setCodeD(cond.getString("code_d"));
                weatherDailyForecast.setCodeN(cond.getString("code_n"));
                weatherDailyForecast.setTextD(cond.getString("txt_d"));
                weatherDailyForecast.setTextN(cond.getString("txt_n"));
                JSONObject tmp = object.getJSONObject("tmp");
                weatherDailyForecast.setTmpMax(tmp.getString("max"));
                weatherDailyForecast.setTmpMin(tmp.getString("min"));

                if (i == 0){
                    weather.setTmpMax(tmp.getString("max"));//设置最高温度
                    weather.setTmpMin(tmp.getString("min"));//设置最低温度
                }
                weatherDailyForecast.setDate(object.getString("date"));
                JSONObject windObject = object.getJSONObject("wind");
                Wind wind = new Wind();
                wind.setDir(windObject.getString("dir"));
                wind.setSc(windObject.getString("sc"));
                wind.setSpeed(windObject.getString("spd"));
                weatherDailyForecast.setWind(wind);
                //Log.d("Utility", "weatherDailyForecast = " + weatherDailyForecast.toString());
                //Log.d("Utility", "wind is = " + wind.toString());
                weatherDailyForecastList.add(weatherDailyForecast);
            }
            weather.setDailyForecastList(weatherDailyForecastList);//设置dailyForecastList

            JSONArray hourlyForecast = fullWeather.getJSONArray("hourly_forecast");
            List<WeatherHourlyForecast> weatherHourlyForecastList = new ArrayList<>();
            for (int i = 0; i < hourlyForecast.length(); i++) {
                WeatherHourlyForecast weatherHourlyForecast = new WeatherHourlyForecast();
                JSONObject object = hourlyForecast.getJSONObject(i);
                weatherHourlyForecast.setTime(object.getString("date"));
                weatherHourlyForecast.setHumidity(object.getString("hum"));
                weatherHourlyForecast.setTmp(object.getString("tmp"));
                Wind wind = new Wind();
                JSONObject windObject = object.getJSONObject("wind");
                wind.setSpeed(windObject.getString("spd"));
                wind.setSc(windObject.getString("sc"));
                wind.setDir(windObject.getString("dir"));
                weatherHourlyForecast.setWind(wind);
                weatherHourlyForecastList.add(weatherHourlyForecast);



            }
            weather.setHourlyForecastList(weatherHourlyForecastList);//设置hourlyForecast

            JSONObject now = fullWeather.getJSONObject("now");
            JSONObject condObject = now.getJSONObject("cond");
            weather.setCond(condObject.getString("code"));//设置天气的图标

            weather.setTmp(now.getString("tmp"));//设置tmp



            WeatherSuggestion weatherSuggestion = new WeatherSuggestion();
            JSONObject suggestionObject = fullWeather.getJSONObject("suggestion");
            JSONObject clothSuggestion = suggestionObject.getJSONObject("drsg");
            JSONObject sportSuggestion = suggestionObject.getJSONObject("sport");
            JSONObject travelSuggestion = suggestionObject.getJSONObject("trav");
            JSONObject fluSuggestion = suggestionObject.getJSONObject("flu");
            weatherSuggestion.setCloth(clothSuggestion.getString("brf"));
            weatherSuggestion.setClothSuggestion(clothSuggestion.getString("txt"));
            weatherSuggestion.setSport(sportSuggestion.getString("brf"));
            weatherSuggestion.setSportSuggestion(sportSuggestion.getString("txt"));
            weatherSuggestion.setTravel(travelSuggestion.getString("brf"));
            weatherSuggestion.setTravelSuggestion(travelSuggestion.getString("txt"));
            weatherSuggestion.setFlu(fluSuggestion.getString("brf"));
            weatherSuggestion.setFluSuggestion(fluSuggestion.getString("txt"));

            weather.setSuggestion(weatherSuggestion);//设置建议
            //Log.d("Utility", "suggestion = " + weatherSuggestion.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public static String subClock(String clock) {
        return (clock.substring(11, clock.length()));
    }


}
