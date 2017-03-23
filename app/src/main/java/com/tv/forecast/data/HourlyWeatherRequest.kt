package com.tv.forecast.data

import com.google.gson.Gson
import java.net.URL

class HourlyWeatherRequest(val townID: String) {

    companion object {
        private val URL = "http://tj.nineton.cn/Heart/index/future24h/?city="
    }

    fun execute(): HourlyForeCastResponse? {
        val hourlyForecastJson = URL(URL + townID).readText()
        try {
            return Gson().fromJson(hourlyForecastJson, HourlyForeCastResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}