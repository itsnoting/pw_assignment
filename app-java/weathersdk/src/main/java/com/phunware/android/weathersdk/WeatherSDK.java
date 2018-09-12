package com.phunware.android.weathersdk;

import com.phunware.android.weathersdk.models.Weather;
import com.phunware.android.weathersdk.api.OpenWeatherAPI;
import com.phunware.android.weathersdk.util.WeatherLiveData;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class WeatherSDK {
    private static WeatherSDK INSTANCE = new WeatherSDK();

    public static WeatherSDK getInstance() {
        return INSTANCE;
    }

    private Retrofit retrofit;
    private OpenWeatherAPI weather;

    private WeatherSDK() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl newRequestURL = originalHttpUrl.newBuilder().addQueryParameter("appid", "7f2297623019f78f11a5761b4ae8871c")
                        .build();

                Request request = original.newBuilder().url(newRequestURL).build();
                return chain.proceed(request);
            }
        });

        retrofit = new Retrofit.Builder().client(httpClient.build())
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        weather = retrofit.create(OpenWeatherAPI.class);
    }

    public WeatherLiveData<Weather> getWeather(@NonNull String zipCode) {
        Call<Weather> call = weather.getWeather(zipCode, "imperial");

        return new WeatherLiveData<>(call);
    }


}
