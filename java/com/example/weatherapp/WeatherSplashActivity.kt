package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.ui.MainActivity

class WeatherSplashActivity : AppCompatActivity() {

    private val MILLISECONDS : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val callMainActivity = Intent(this@WeatherSplashActivity, MainActivity::class.java)
            startActivity(callMainActivity)
            finish()
        }, MILLISECONDS)
    }
}