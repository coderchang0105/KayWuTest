package com.coderchang.kaywuzhihu.domain;

/**
 * Created by coderchang on 2016/6/3.
 */
public class Province {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Province() {
    }

    public Province(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
