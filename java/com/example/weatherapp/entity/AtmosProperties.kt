package com.example.weatherapp.entity

import com.google.gson.annotations.SerializedName

data class AtmosProperties(
    @SerializedName("temp")
    val temperature : Double
)