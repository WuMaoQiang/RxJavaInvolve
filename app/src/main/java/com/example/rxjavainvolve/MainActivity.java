package com.example.rxjavainvolve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rxjavainvolve.viewmodel.ActivityReceive;
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
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "xiaoqiang";
    private TextView mTv;
    private RecyclerView mRecyclerView;
    private final String BASEURL = "https://wanandroid.com/";
    ApiService mApiService;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//       flatMap();
//        zip();
//        baseTest();

        LogcatHelper.getInstance(this).start();


    }

    private void baseTest() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("aaa");

            emitter.onNext("bbb");
            emitter.onNext("ccc");
            emitter.onNext("ddd");
            emitter.onComplete();

        }).subscribe(new Observer<String>() {
            private Disposable mDispose;

            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
                mDispose = d;
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext: " + s);
                if ("ccc".equals(s)) {
                    mDispose.dispose();
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });

    }


    private void zip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        });

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        });

        Observable.zip(observable1, observable2, (integer, s) -> integer + s).subscribe(s -> Log.i(TAG, "accept: " + s), throwable -> {

        });
    }

    private void flatMap() {

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
        regist.flatMap((Function<RetrofitBean, ObservableSource<NextBean>>) retrofitBean -> mApiService.getInfonextBean()).subscribeOn(Schedulers.io())//指定网络请求在io后台线程中进行
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
        List<String> datas = new ArrayList<>();
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");
        datas.add("qqqqq");

        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(v -> LogcatHelper.getInstance(this).stop());
        mRecyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 6);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (i < 4) {
                    return 3;
                }
                return 2;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new GeneralAdapter(this, datas));
    }
}
