package com.example.weatherapp.networkOperation

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class RetrofitInstance {

    companion object {
        private const val URL_END_POINT : String = "https://community-open-weather-map.p.rapidapi.com"

        fun getInstance(): Retrofit {
            val client : OkHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .baseUrl(URL_END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
    }

}