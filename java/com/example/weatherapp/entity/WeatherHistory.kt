package com.example.weatherapp.entity

import com.google.gson.annotations.SerializedName

data class WeatherHistory(
    @SerializedName("list")
    val weatherHistory: List<WeatherWithDataAndTime>
)