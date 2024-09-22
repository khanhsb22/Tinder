package com.example.tinder_app.swipe;

public class SwipeModel {
    public SwipeCardModel top;
    public SwipeCardModel bottom;

    public SwipeModel(SwipeCardModel top, SwipeCardModel bottom) {
        this.top = top;
        this.bottom = bottom;
    }
}
