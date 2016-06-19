package com.coderchang.kaywuzhihu.fragment;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.activity.ChooseCityActivity;
import com.coderchang.kaywuzhihu.adapter.WeatherAdapter;
import com.coderchang.kaywuzhihu.domain.Weather;
import com.coderchang.kaywuzhihu.task.LoadWeatherTask;

/**
 * Created by coderchang on 2016/5/28.
 */
public class WeatherFragment extends Fragment{

    public static final int RESULT_OK = 1;
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.weather_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getWeather("无锡市");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                getWeather(data.getStringExtra("cityName"));
                break;
        }

    }


    public void getWeather(String cityName) {
        new LoadWeatherTask(new LoadWeatherTask.OnFinishListener() {
            @Override
            public void onFinish(Weather weather) {
                WeatherAdapter adapter = new WeatherAdapter(getActivity(), weather);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnCityClickListener(new WeatherAdapter.OnCityClickListener() {
                    @Override
                    public void onClick() {
                        startActivityForResult(new Intent(getActivity(), ChooseCityActivity.class),1);
                    }
                });


                Toast.makeText(getActivity(), "更新天气完成", Toast.LENGTH_SHORT).show();
            }
        }).execute(cityName);
    }
}
