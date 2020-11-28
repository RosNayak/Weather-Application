package com.example.weatherapp.entity

import com.google.gson.annotations.SerializedName

data class WeatherWithDataAndTime(
    val weather : List<WeatherDescription>,
    @SerializedName("main")
    val temperatureData : AtmosProperties,
    @SerializedName("dt_txt")
    val dateTime : String
)