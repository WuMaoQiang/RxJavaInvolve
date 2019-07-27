package com.example.rxjavainvolve.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/26 20:03
 */
public class MainPresenter implements LifecycleObserver {

    public static final String TAG = "LifeCycleAAA";
    public String name;

    public MainPresenter(String name) {
        this.name = name;
    }

    //通过运行时注解的方式实现生命周期，是不是很像EventBus

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onStssssssssara() {//方法名无所谓，只要Event.ON_CREATE来对应生命周期
        Log.d(TAG, name + "onCreate..");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, name + "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, name + "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.d(TAG, name + "onStop");
    }
    //比较特殊的一个回调会在任何生命周期回调的时候都回调这里
    //@OnLifecycleEvent(Lifecycle.Event.ON_ANY) public void onDestroy() {
    //  Log.d(TAG, "ON_ANY");
    //}
}
