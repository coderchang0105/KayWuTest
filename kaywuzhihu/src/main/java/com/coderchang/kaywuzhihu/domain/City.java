package com.coderchang.kaywuzhihu.domain;

/**
 * Created by coderchang on 2016/6/3.
 */
public class City {
    private String name;
    private String provinceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String id) {
        this.provinceId = id;
    }

    public City() {
    }

    public City(String name, String provinceId) {
        this.name = name;
        this.provinceId = provinceId;
    }
}
