package com.phunware.android.phunwareproducthomework.features.detail;

import com.phunware.android.weathersdk.WeatherSDK;
import com.phunware.android.weathersdk.models.Weather;
import com.phunware.android.weathersdk.util.WeatherLiveData;

import androidx.lifecycle.ViewModel;

public class ZipCodeDetailViewModel extends ViewModel {
    private String zipCode;

    public WeatherLiveData<Weather> getWeather(String zipCode) {
        this.zipCode = zipCode;

        return WeatherSDK.getInstance().getWeather(zipCode);
    }
}
