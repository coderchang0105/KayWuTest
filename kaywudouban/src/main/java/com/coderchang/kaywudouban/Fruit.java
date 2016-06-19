package com.coderchang.kaywudouban;

/**
 * Created by coderchang on 2016/5/25.
 */
public class Fruit {
    private int fruitResourceId;
    private String fruitName;

    public Fruit(int fruitResourceId, String fruitName) {
        this.fruitResourceId = fruitResourceId;
        this.fruitName = fruitName;
    }

    public int getFruitResourceId() {
        return fruitResourceId;
    }

    public void setFruitResourceId(int fruitResourceId) {
        this.fruitResourceId = fruitResourceId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
}
