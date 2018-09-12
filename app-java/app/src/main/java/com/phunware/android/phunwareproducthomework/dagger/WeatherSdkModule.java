package com.phunware.android.phunwareproducthomework.dagger;

import com.phunware.android.weathersdk.WeatherSDK;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherSdkModule {
    @Provides
    @Singleton
    WeatherSDK provideWeatherSdk() {
        return WeatherSDK.getInstance();
    }
}
