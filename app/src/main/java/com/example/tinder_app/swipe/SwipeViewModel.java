package com.example.tinder_app.swipe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tinder_app.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipeViewModel extends ViewModel {
    public SwipeViewModel() {
        updateStream();
    }

    private MutableLiveData<SwipeModel> stream = new MutableLiveData<>();

    public MutableLiveData<SwipeModel> getStream() {
        return stream;
    }

    private ArrayList<SwipeCardModel> data = new ArrayList<>(Arrays.asList(
            new SwipeCardModel(new ArrayList<>(Arrays.asList(R.drawable.suni1, R.drawable.suni2)),
                    "Suni", 28,
                    "Ha Noi", 10),
            new SwipeCardModel(new ArrayList<>(Arrays.asList(R.drawable.taylor1, R.drawable.taylor2,
                    R.drawable.taylor3))
                    , "Taylor Swift", 35,
                    "USA", 20),
            new SwipeCardModel(new ArrayList<>(Arrays.asList(R.drawable.baoanh1, R.drawable.baoanh2,
                    R.drawable.baoanh3, R.drawable.baoanh4, R.drawable.baoanh5))
                    , "Bao Anh", 32,
                    "Ho Chi Minh", 5),
            new SwipeCardModel(new ArrayList<>(Arrays.asList(R.drawable.hoangyen1, R.drawable.hoangyen2,
                    R.drawable.hoangyen3))
                    , "Duong Hoang Yen", 26,
                    "Ha Noi", 15),
            new SwipeCardModel(new ArrayList<>(Arrays.asList(R.drawable.marion_raven_1, R.drawable.marion_raven_2,
                    R.drawable.marion_raven_3, R.drawable.marion_raven_4, R.drawable.marion_raven_5,
                    R.drawable.marion_raven_6, R.drawable.marion_raven_7))
                    , "Marion Raven", 37,
                    "Canada", 45)
    )
    );

    private int currentIndex = 0;

    private SwipeCardModel getTopCard() {
        return data.get(currentIndex % data.size());
    }

    private SwipeCardModel getBottomCard() {
        return data.get((currentIndex + 1) % data.size());
    }

    public void swipe() {
        currentIndex += 1;
        updateStream();
    }

    private void updateStream() {
        stream.setValue(new SwipeModel(
                getTopCard(),
                getBottomCard()
        ));
    }
}
