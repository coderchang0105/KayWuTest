package com.coderchang.kaywuzhihu.http;

import android.nfc.Tag;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by coderchang on 2016/5/27.
 */
public class Http {
    public static final String NEWS_URL = "http://news-at.zhihu.com/api/4/news/before/";
    public static final String NEWS_LATEST_URL = "http://news-at.zhihu.com/api/4/news/latest";
    public static final String NEWS_DETAIL = "http://news-at.zhihu.com/api/4/news/";
    public static final String TAG = "Http";
    public static final String WEATHER_KEY = "4fb297ff862b4aa5aaf1d05a5ba15cc5";
    public static final String WEATHER_UTL = "https://api.heweather.com/x3/weather?";
    public static final String WEATHER_CITY_ACTION = "city";
    public static final String WEATHER_KEY_ACTION = "key";
    public static String get(String urlAddress) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);

            }
            Log.d("TAG", response.toString());
            reader.close();
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * 获取最新消息
     * @return
     */
    public static String getNewsLatest() {
        return get(NEWS_LATEST_URL);
    }

    /**
     * 获取每一天消息的细节
     * @param id
     * @return
     */
    public static String getNewsDetail(int id) {
        return get(NEWS_DETAIL + id);
    }

    public static String getDateNews(int date) {
        return get(NEWS_URL + date);
    }

    public static String getWeatherString(String cityName) {
        String weatherFullUrl = WEATHER_UTL + WEATHER_CITY_ACTION + "=" + cityName + "&" + WEATHER_KEY_ACTION
                + "=" + WEATHER_KEY;
        String weatherString = get(weatherFullUrl);
        Log.d("Http", "weatherFullUrl = " + weatherFullUrl);
        Log.d("Http", "WeatherString = " + weatherString);
        return weatherString;

    }
}
