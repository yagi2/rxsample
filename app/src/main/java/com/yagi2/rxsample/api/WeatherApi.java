package com.yagi2.rxsample.api;

import com.yagi2.rxsample.model.WeatherModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface WeatherApi {

    @GET("json/v1")
    Observable<WeatherModel> getWeather(@Query("city") String cityNumber);
}
