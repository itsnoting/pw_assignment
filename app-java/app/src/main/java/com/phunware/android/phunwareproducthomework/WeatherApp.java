package com.phunware.android.phunwareproducthomework;

import android.app.Application;

import com.phunware.android.phunwareproducthomework.dagger.AppComponent;
import com.phunware.android.phunwareproducthomework.dagger.DaggerAppComponent;
import com.phunware.android.phunwareproducthomework.dagger.RoomModule;
import com.phunware.android.phunwareproducthomework.dagger.SharedPreferencesModule;
import com.phunware.android.phunwareproducthomework.room.data.ZipCode;
import com.phunware.android.phunwareproducthomework.room.repository.ZipCodeRepository;
import com.phunware.android.phunwareproducthomework.storage.ZipCodeStore;
import com.phunware.android.weathersdk.WeatherSDK;

import javax.inject.Inject;

public class WeatherApp extends Application {
    private static AppComponent applicationComponent;
    @Inject
    ZipCodeStore zipCodeStore;

    @Inject
    ZipCodeRepository zipCodeRepository;

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
                .roomModule(new RoomModule(this))
                .application(this).build();
        applicationComponent.inject(this);
        String[] defaultZips = {"92029", "92128", "03872"};
        if (zipCodeRepository.getAll().size() == 0) {
            for (String zip : defaultZips) {
                    zipCodeRepository.insert(new ZipCode(zip));
            }
        }

    }
}
