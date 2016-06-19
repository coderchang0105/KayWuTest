package com.coderchang.kaywuzhihu.task;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.coderchang.kaywuzhihu.domain.Weather;
import com.coderchang.kaywuzhihu.http.Http;
import com.coderchang.kaywuzhihu.utility.Utility;

/**
 * Created by coderchang on 2016/6/6.
 */
public class LoadWeatherTask extends AsyncTask<String, Void, Weather> {
    private OnFinishListener mListener;
    public static final String TAG = "LoadWeatherTask";

    public LoadWeatherTask(LoadWeatherTask.OnFinishListener listener) {
        mListener = listener;
    }

    @Override
    protected Weather doInBackground(String... params) {

        String weatherString = Http.getWeatherString(Utility.subCityName(params[0]));
        Weather weather = Utility.parseJsonToWeather(weatherString);
        //Log.d(TAG, "doInBackground() called with: " + "params = [" + params[0] + "]");
        Log.d(TAG, "weather = " + weather.toString());
        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        if (mListener != null) {
            mListener.onFinish(weather);
        }
    }

    public interface OnFinishListener {
        void onFinish(Weather weather);
    }

}
