package com.phunware.android.weathersdk.models;

import com.squareup.moshi.Json;

public class Temperatures {
    private double temp;
    @Json(name = "temp_min")
    private double tempMin;
    @Json(name = "temp_max")
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}
