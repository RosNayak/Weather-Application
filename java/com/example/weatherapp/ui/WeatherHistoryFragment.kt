package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.entity.WeatherHistory
import com.example.weatherapp.entity.WeatherWithDataAndTime
import com.example.weatherapp.usecase.GetWeatherDataUsecase
import kotlinx.android.synthetic.main.fragment_weather_history.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherHistoryFragment : Fragment() {

    private lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = requireArguments().getString("cityName").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val weatherHistoryCall : Call<WeatherHistory> = GetWeatherDataUsecase.getWeatherHistory(name)
        weatherHistoryCall.enqueue(weatherHistoryCallback)
    }

    private val weatherHistoryCallback = object : Callback<WeatherHistory> {

        override fun onFailure(call: Call<WeatherHistory>, t: Throwable) {
            heading.text = "Data could not be fetched. Check your internet connection and then retry."
            call.cancel()
        }

        override fun onResponse(
            call: Call<WeatherHistory>,
            response: Response<WeatherHistory>
        ) {
            if (response.code() == 200) {
                heading.text = name

                val historyData : WeatherHistory? = response.body()

                val data = historyData?.weatherHistory
                val adapter = WeatherHistoryRecyclerView(
                    context as Context,
                    data as List<WeatherWithDataAndTime>
                )
                val layoutManager = LinearLayoutManager(activity)
                historyView.layoutManager = layoutManager
                historyView.adapter = adapter
            } else {
                heading.text = "Place does not exist."
            }
        }

    }
}