package com.coderchang.kaywuzhihu.activity;

import android.app.Activity;
import android.app.admin.DeviceAdminInfo;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;

import com.coderchang.kaywuzhihu.R;
import com.coderchang.kaywuzhihu.adapter.ChooseCityAdapter;
import com.coderchang.kaywuzhihu.db.DBManager;
import com.coderchang.kaywuzhihu.db.WeatherDB;
import com.coderchang.kaywuzhihu.domain.City;
import com.coderchang.kaywuzhihu.domain.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderchang on 2016/6/8.
 */
public class ChooseCityActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private DBManager mManager;
    private WeatherDB mDB;
    public static final int RESULT_OK = 1;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_PROVINCE = 0;

    private int level = 0;
    private ChooseCityAdapter mAdapter;

    private SQLiteDatabase database;

    private List<String> data = new ArrayList<>();

    private List<Province> provinceList = new ArrayList<>();

    private List<City> cityList = new ArrayList<>();

    private City selectCity;
    private Province selectProvince;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_city_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mManager= new DBManager(this);
        mDB = new WeatherDB(this);
        database = mManager.getWeatherDatabase();
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.city_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));




        mAdapter.setOnItemClickListener(new ChooseCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int itemPos) {
                if (level == LEVEL_PROVINCE) {
                    selectProvince = provinceList.get(itemPos);
                    level = LEVEL_CITY;
                    cityList.clear();
                    cityList = mDB.getCityList(mManager.getWeatherDatabase(), selectProvince.getId());
                    data.clear();
                    for (int i = 0; i < cityList.size(); i++) {
                        data.add(cityList.get(i).getName());
                    }
                    mAdapter.notifyDataSetChanged();
                    toolbar.setTitle("市");
                } else if (level == LEVEL_CITY) {
                    selectCity = cityList.get(itemPos);
                    Intent intent = new Intent();
                    intent.putExtra("cityName", selectCity.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mAdapter = new ChooseCityAdapter(this, data);
        provinceList.clear();
        provinceList = mDB.getProvinceList(mManager.getWeatherDatabase());
        for (int i = 0; i < provinceList.size(); i++) {
            data.add(provinceList.get(i).getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        level = LEVEL_PROVINCE;
        data.clear();
        for (int i = 0; i < provinceList.size(); i++) {
            data.add(provinceList.get(i).getName());
        }
        mAdapter.notifyDataSetChanged();
    }
}
