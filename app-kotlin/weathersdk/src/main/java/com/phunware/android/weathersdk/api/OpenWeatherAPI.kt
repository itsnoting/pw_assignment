package com.phunware.android.weathersdk.api

import com.phunware.android.weathersdk.models.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("data/2.5/weather")
    fun getWeather(@Query("zip") zipCode: String, @Query("units") units: String = "imperial"): Call<Weather>
}