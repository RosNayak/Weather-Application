package com.example.weatherapp.usecase

import com.example.weatherapp.entity.WeatherHistory
import com.example.weatherapp.entity.WeatherToday
import com.example.weatherapp.networkOperation.RetrofitInstance
import com.example.weatherapp.networkOperation.RetrofitInterface
import retrofit2.Call

class GetWeatherDataUsecase {

    companion object {
        private val retrofitImplemented = RetrofitInstance.getInstance().create(RetrofitInterface::class.java)

        fun getCurrentWeather(city : String) : Call<WeatherToday> {
            return retrofitImplemented.getCurrentWeatherDetails(city)
        }

        fun getWeatherHistory(city : String) : Call<WeatherHistory> {
            return retrofitImplemented.getFiveDaysWeatherData(city)
        }
    }


}