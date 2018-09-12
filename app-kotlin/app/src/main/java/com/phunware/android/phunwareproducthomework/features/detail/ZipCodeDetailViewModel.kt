package com.phunware.android.phunwareproducthomework.features.detail

import androidx.lifecycle.ViewModel
import com.phunware.android.weathersdk.util.WeatherLiveData
import com.phunware.android.weathersdk.WeatherSDK
import com.phunware.android.weathersdk.models.Weather

class ZipCodeDetailViewModel : ViewModel() {
    private lateinit var zipCode: String

    private val liveData: WeatherLiveData<Weather> by lazy {
        WeatherSDK.getWeather(zipCode)
    }

    fun getWeather(zipCode: String): WeatherLiveData<Weather> {
        this.zipCode = zipCode

        return liveData
    }
}