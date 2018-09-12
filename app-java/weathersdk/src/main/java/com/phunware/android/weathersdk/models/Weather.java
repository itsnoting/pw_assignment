package com.phunware.android.weathersdk.models;

import com.squareup.moshi.Json;

public class Weather {
    private String name;
    @Json(name = "sys")
    private LocationInfo locationInfo;
    @Json(name = "main")
    private Temperatures temperatures;

    public String getName() {
        return name;
    }

    public LocationInfo getLocationInfo() {
        return locationInfo;
    }

    public Temperatures getTemperatures() {
        return temperatures;
    }
}
