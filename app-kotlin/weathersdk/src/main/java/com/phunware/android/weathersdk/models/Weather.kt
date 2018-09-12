package com.phunware.android.weathersdk.models

import com.squareup.moshi.Json

data class Weather(val name: String,
                   @field:Json(name = "sys") val locationInfo: LocationInfo,
                   @field:Json(name = "main") val temperatures: Temperatures
)

data class LocationInfo(val country: String,
                        val sunrise: Long,
                        val sunset: Long)

data class Temperatures(val temp: Double,
                        @field:Json(name = "temp_min") val tempMin: Double,
                        @field:Json(name = "temp_max") val tempMax: Double)