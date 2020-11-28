package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.entity.WeatherToday
import com.example.weatherapp.handlers.SharedPreferenceHandler
import com.example.weatherapp.usecase.GetWeatherDataUsecase
import kotlinx.android.synthetic.main.fragment_default_weather_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultWeatherCityFragment : Fragment(),
    ChangeDefaultCityDialog.ChangeDefaultCityDialogInterface {

    private lateinit var navController : NavController
    private lateinit var cityName : String
    private val sharedPreferences by lazy { SharedPreferenceHandler(context as Context) }
    private val DIALOG_TAG = "Change Default City Dialog"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_default_weather_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        setSearchCityButtonListener()
        getDefaultCityName()
        setWeatherDataForTextViews()
        setCallDialogButtonListener()
    }

    private fun setSearchCityButtonListener() {
        searchCities.setOnClickListener {
            navController.navigate(R.id.action_defaultWeatherCityFragment_to_enterCityFragment)
        }
    }

    private fun getDefaultCityName() {
        cityName = sharedPreferences.getDefaultCity().toString()
    }

    private fun setWeatherDataForTextViews() {
        val weatherData = GetWeatherDataUsecase.getCurrentWeather(cityName)
        weatherData.enqueue(weatherDataCallback)
    }

    private val weatherDataCallback = object : Callback<WeatherToday> {
        override fun onFailure(call: Call<WeatherToday>, t: Throwable) {
            defaultCityName.text = "Could not fetch the data. Check Internet connection."
        }

        override fun onResponse(call: Call<WeatherToday>, response: Response<WeatherToday>) {
            if (response.code() == 200) {
                val weatherData = response.body()

                defaultCityName.text = cityName
                val modifyTemp = (weatherData?.temperatureData?.temperature?.toInt()
                    ?.minus(273)).toString() + " 'C"
                defaultCityTemperature.text = modifyTemp
                defaultCityWeatherDescription.text = weatherData?.weather?.get(0)?.description

            } else {
                defaultCityName.text = "City not found."
            }
        }
    }

    private fun setCallDialogButtonListener() {
        changeDefaultCity.setOnClickListener {
            val alertDialog = ChangeDefaultCityDialog()
            alertDialog.setDialogListener(this)
            alertDialog.show(parentFragmentManager, DIALOG_TAG)
        }
    }

    override fun onConfirmButtonClicked(newCity : String) {
        sharedPreferences.setDefaultCityName(newCity)
        Toast.makeText(requireContext(), "Default city changed.", Toast.LENGTH_SHORT).show()
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, DefaultWeatherCityFragment()).commit() //reload fragment.
    }

    override fun onCancelButtonClicked() {
        Toast.makeText(requireContext(), "Default city not changed.", Toast.LENGTH_SHORT).show()
    }
}