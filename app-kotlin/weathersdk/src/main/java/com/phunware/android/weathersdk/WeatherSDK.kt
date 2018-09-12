package com.phunware.android.weathersdk

import com.phunware.android.weathersdk.api.OpenWeatherAPI
import com.phunware.android.weathersdk.models.Weather
import com.phunware.android.weathersdk.util.WeatherLiveData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object WeatherSDK {
    private var retrofit: Retrofit
    private var weather: OpenWeatherAPI

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appid", "7f2297623019f78f11a5761b4ae8871c")
                    .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        weather = retrofit.create(OpenWeatherAPI::class.java)
    }

    fun getWeather(zipCode: String): WeatherLiveData<Weather> {
        val call = weather.getWeather(zipCode)

        return WeatherLiveData(call)
    }
}