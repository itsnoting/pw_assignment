package com.phunware.android.phunwareproducthomework.features.detail;

import com.phunware.android.phunwareproducthomework.WeatherApp;
import com.phunware.android.weathersdk.WeatherSDK;
import com.phunware.android.weathersdk.models.Weather;
import com.phunware.android.weathersdk.util.WeatherLiveData;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class ZipCodeDetailViewModel extends ViewModel {
    private String zipCode;
    private String unit;

    @Inject
    WeatherSDK weatherSDK;

    public ZipCodeDetailViewModel() {
        WeatherApp.getApplicationComponent().inject(this);
    }

    public WeatherLiveData<Weather> getWeather(String zipCode, String unit) {
        this.zipCode = zipCode;
        this.unit = unit;

        return weatherSDK.getWeather(zipCode, unit);
    }
}
