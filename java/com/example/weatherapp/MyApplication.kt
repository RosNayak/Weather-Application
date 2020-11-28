package com.example.weatherapp

import android.app.Application
import android.content.Context
import com.example.weatherapp.handlers.SharedPreferenceHandler
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        setRealmDefaultConfig()

        SharedPreferenceHandler.setPreference(applicationContext.getSharedPreferences(
            SharedPreferenceHandler.PREFERENCE_NAME,
            Context.MODE_PRIVATE)
        )
    }

    private fun setRealmDefaultConfig() {
        val realmConfiguration = RealmConfiguration.Builder()
        val configuration = realmConfiguration.name("WeatherApp.realm")
            .schemaVersion(1)
            .build()

        Realm.setDefaultConfiguration(configuration)
    }
}