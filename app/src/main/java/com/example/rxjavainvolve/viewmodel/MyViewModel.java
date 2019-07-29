package com.example.rxjavainvolve.viewmodel;

import com.example.rxjavainvolve.bean;

import io.reactivex.subjects.PublishSubject;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/29 16:48
 */
public class MyViewModel {
    private static MyViewModel sInstance;
    private PublishSubject<bean> mTabMainIndicatorListSubject;

    private MyViewModel() {
        mTabMainIndicatorListSubject = PublishSubject.create();
    }

    public PublishSubject<bean> getTabMainIndicatorListSubject() {
        return mTabMainIndicatorListSubject;
    }


    public void send() {
        bean bean = new bean();
        bean.setNane("a");
        mTabMainIndicatorListSubject.onNext(bean);
    }

    public static MyViewModel getInstance() {
        if (null == sInstance) {
            sInstance = new MyViewModel();
        }
        return sInstance;
    }


}



