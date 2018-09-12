package com.phunware.android.phunwareproducthomework.storage;

import android.content.SharedPreferences;

import com.phunware.android.phunwareproducthomework.model.UnitEnum;

import javax.inject.Inject;

public class SettingsSharedPreferences {

    private SharedPreferences mSharedPreferences;

    @Inject
    public SettingsSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
        if (!mSharedPreferences.contains("unit")) {
            putData("unit", UnitEnum.farenheit.getUnitSystem());
        }
    }

    public  void putData(String key, String data) {
        mSharedPreferences.edit().putString(key, data).apply();
    }

    public String getData(String key) {
        return mSharedPreferences.getString(key,null);
    }
}
