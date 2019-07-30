package com.example.rxjavainvolve.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.rxjavainvolve.R;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/29 16:53
 */
public class ActivityReceive extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyViewModel.getInstance()
                .getTabMainIndicatorListSubject()
                .subscribe(
                        bean -> Log.i("xiaoqiang", "accept: "+bean.getNane())
                );
        MyViewModel.getInstance().send();

    }
}
