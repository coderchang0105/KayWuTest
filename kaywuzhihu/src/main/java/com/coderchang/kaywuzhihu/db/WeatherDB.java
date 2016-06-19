package com.coderchang.kaywuzhihu.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coderchang.kaywuzhihu.domain.City;
import com.coderchang.kaywuzhihu.domain.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderchang on 2016/6/3.
 */
public class WeatherDB {

    private Context mContext;


    public WeatherDB(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 从数据库中读取省的列表
     * @param database
     * @return
     */
    public List<Province> getProvinceList(SQLiteDatabase database) {
        List<Province> provinceList = new ArrayList<>();
        Cursor cursor = database.query("T_Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setName(cursor.getString(cursor.getColumnIndex("ProName")));
                province.setId(cursor.getString(cursor.getColumnIndex("ProSort")));
                provinceList.add(province);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return provinceList;
    }

    /**
     * 通过省的id来获取这个省的所有市
     * @param database
     * @param provinceId
     * @return
     */
    public List<City> getCityList(SQLiteDatabase database,String provinceId) {
        List<City> cityList = new ArrayList<>();
        Cursor cursor = database.query("T_City", null, "ProID = ?", new String[]{provinceId}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setName(cursor.getString(cursor.getColumnIndex("CityName")));
                city.setProvinceId(provinceId);
                cityList.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cityList;
    }
}
