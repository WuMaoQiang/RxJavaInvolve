package com.example.rxjavainvolve.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavainvolve.R;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/26 20:05
 */
public class LifeCycleActivity extends AppCompatActivity {
    private static final String TAG = "LifeCycleAAA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle);
        MainPresenter mainPresenter = new MainPresenter(TAG);
        //把MainPresenter交给LifeCycle管理就OK了
        getLifecycle().addObserver(mainPresenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }



}
