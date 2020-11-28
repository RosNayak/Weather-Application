package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.entity.WeatherToday
import com.example.weatherapp.handlers.SharedPreferenceHandler
import com.example.weatherapp.usecase.CitySearchHistoryUsecase
import com.example.weatherapp.usecase.GetWeatherDataUsecase
import kotlinx.android.synthetic.main.fragment_enter_city.*
import kotlinx.android.synthetic.main.fragment_weather_history.*
import kotlinx.android.synthetic.main.today_weather_fragment.*
import retrofit2.Response

class TodayWeatherFragment : Fragment() {

    private lateinit var name : String
    private lateinit var navController: NavController
    private val sharedPreferences : SharedPreferenceHandler by lazy { SharedPreferenceHandler(context as Context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = requireArguments().getString("cityName").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val weatherData = GetWeatherDataUsecase.getCurrentWeather(name)
        weatherData.enqueue(weatherTodayCallback)
        seeHistory.setOnClickListener(moveToWeatherHistoryFragmentListener)
    }

    private val weatherTodayCallback = object : retrofit2.Callback<WeatherToday> {
        override fun onFailure(call: retrofit2.Call<WeatherToday>, t: Throwable) {
            cityName.text = "Data could not be fetched. Check internet connection and retry."
            call.cancel()
        }

        override fun onResponse(
            call: retrofit2.Call<WeatherToday>,
            response: Response<WeatherToday>
        ) {
            if (response.code() == 200) {
                val weatherToday : WeatherToday? = response.body()
                cityName.text = name
                val modifyTemp = (weatherToday?.temperatureData?.temperature?.toInt()
                    ?.minus(273)).toString() + " 'C"
                temperature.text = modifyTemp
                description.text = weatherToday?.weather?.get(0)?.description

                CitySearchHistoryUsecase().addCityToSearchHistory(name)
            } else {
                cityName.text = "Place does not exist."
            }
        }
    }

    private val moveToWeatherHistoryFragmentListener = View.OnClickListener {
        val bundle: Bundle = bundleOf("cityName" to name)
        navController.navigate(R.id.action_todayWeatherFragment_to_weatherHistoryFragment, bundle)
    }

}