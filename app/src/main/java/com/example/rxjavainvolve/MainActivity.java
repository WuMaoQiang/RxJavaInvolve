package com.example.rxjavainvolve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "xiaoqiang";
    private TextView mTv;
    private final String BASEURL = "https://wanandroid.com/";
    ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Retrofit retrofit = new Retrofit.Builder()
                //服务器地址
                .baseUrl(BASEURL)
                //配置转化库，采用Gson
                .addConverterFactory(GsonConverterFactory.create())
                //配置回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置OKHttpClient为网络客户端
                .build();
           //获取API接口 通过flatMap走了两个请求
        mApiService = retrofit.create(ApiService.class);
        Observable<RetrofitBean> regist = mApiService.getInfoRxjava();
        regist.flatMap(new Function<RetrofitBean, ObservableSource<NextBean>>() {
            @Override
            public ObservableSource<NextBean> apply(RetrofitBean retrofitBean) throws Exception {

                return mApiService.getInfonextBean();
            }
        }).subscribeOn(Schedulers.io())//指定网络请求在io后台线程中进行
                .observeOn(AndroidSchedulers.mainThread())//指定observer回调在UI主线程中进行
                .subscribe(new Observer<NextBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NextBean s) {
                        Log.i(TAG, "onNext: " + s.getData().get(0).getOrder());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });//发起请求

    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }
}
