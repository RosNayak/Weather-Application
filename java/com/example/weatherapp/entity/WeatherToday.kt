package com.example.weatherapp.entity

import com.google.gson.annotations.SerializedName

data class WeatherToday(
    val weather : List<WeatherDescription>,
    @SerializedName("main")
    val temperatureData : AtmosProperties
)