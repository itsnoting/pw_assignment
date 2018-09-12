package com.phunware.android.weathersdk.api;

import com.phunware.android.weathersdk.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {
    @GET("data/2.5/weather")
    Call<Weather> getWeather(@Query("zip") String zipCode, @Query("units") String units);
}
