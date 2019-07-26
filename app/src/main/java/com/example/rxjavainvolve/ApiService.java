package com.example.rxjavainvolve;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/26 14:14
 * username,password,repassword
 */
public interface ApiService {
    @GET("wxarticle/chapters/json")
    Observable<RetrofitBean> getInfoRxjava();
    @GET("project/tree/json")
    Observable<NextBean> getInfonextBean();

}
