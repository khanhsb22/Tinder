package com.example.tinder_app.swipe;

import java.util.ArrayList;

public class SwipeCardModel {
    public ArrayList<Integer> images;
    public String name;
    public int age;
    public String address;
    public int distance; // km

    public SwipeCardModel(ArrayList<Integer> images, String name, int age, String address, int distance) {
        this.images = images;
        this.name = name;
        this.age = age;
        this.address = address;
        this.distance = distance;
    }
}
