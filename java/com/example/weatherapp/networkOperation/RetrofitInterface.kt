package com.example.weatherapp.networkOperation

import com.example.weatherapp.entity.WeatherHistory
import com.example.weatherapp.entity.WeatherToday
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitInterface {

    @Headers(
        "x-rapidapi-host: community-open-weather-map.p.rapidapi.com",
        "x-rapidapi-key: c036f39312mshb9bb4512a914c33p173f2ajsn77a942d50aa2"
    )
    @GET("/weather")
    fun getCurrentWeatherDetails(@Query("q") cityName : String) : Call<WeatherToday>

    @Headers(
        "x-rapidapi-host: community-open-weather-map.p.rapidapi.com",
        "x-rapidapi-key: c036f39312mshb9bb4512a914c33p173f2ajsn77a942d50aa2"
    )
    @GET("/forecast")
    fun getFiveDaysWeatherData(@Query("q") cityName: String) : Call<WeatherHistory>

}