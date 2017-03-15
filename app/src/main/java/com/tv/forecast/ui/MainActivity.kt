package com.tv.forecast.ui

import android.app.Activity
import android.os.Bundle
import com.tv.forecast.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        message.text = getString(R.string.weather_forecast_for_tv)
    }
}
