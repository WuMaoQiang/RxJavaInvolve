package com.example.rxjavainvolve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "xiaoqiang";
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Observable.intervalRange(2, 5, 2, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
            @Override
            public String apply(Long i) {
                return "123"+i;
            }
        }).flatMap(new Function<String, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(String s) throws Exception {
                int i = Integer.parseInt(s);
                Log.i(TAG, "apply: "+i);
                return Observable.timer(2,TimeUnit.SECONDS);
            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "===============onNext " + aLong);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }
}
