package com.phunware.android.phunwareproducthomework;

import android.app.Application;

import com.phunware.android.phunwareproducthomework.dagger.AppComponent;
import com.phunware.android.phunwareproducthomework.dagger.DaggerAppComponent;
import com.phunware.android.phunwareproducthomework.dagger.SharedPreferencesModule;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;
import com.phunware.android.weathersdk.WeatherSDK;

import javax.inject.Inject;

public class WeatherApp extends Application {
    private static AppComponent applicationComponent;
    @Inject
    ZipCodeStore zipCodeStore;

    @Inject
    WeatherSDK weatherSDK;

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .application(this).build();
        applicationComponent.inject(this);

        zipCodeStore.addZipCode("92029");
        zipCodeStore.addZipCode("92128");
        zipCodeStore.addZipCode("03872");
    }
}
