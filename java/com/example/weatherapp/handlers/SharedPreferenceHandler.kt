package com.example.weatherapp.handlers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHandler(context: Context) {

    companion object {
        val PREFERENCE_NAME : String = "Weather Preference"
        private lateinit var preferenceObject : SharedPreferences

        fun setPreference(preferences: SharedPreferences) {
            preferenceObject = preferences
        }
    }

    //keys.
    private val DEFAULT_CITY_NAME : String = "Default City"

    //default values.
    private val DEFAULT_CITY_DEFAULT_VALUE : String = "Manipal"

    fun getDefaultCity(): String? {
        return preferenceObject.getString(DEFAULT_CITY_NAME, DEFAULT_CITY_DEFAULT_VALUE)
    }

    fun setDefaultCityName(newCity : String) {
        val editor = preferenceObject.edit()
        editor.putString(DEFAULT_CITY_NAME, newCity)
        editor.apply()
    }
}