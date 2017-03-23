package com.tv.forecast.data

import android.util.Log
import com.google.gson.Gson
import com.tv.forecast.domain.mappers.ForecastDataMapper
import com.tv.forecast.domain.model.WeatherInfo
import java.net.URL

class WeatherRequest(val townID: String) {

    companion object {
        private val URL = "http://tj.nineton.cn/Heart/index/all?city="
    }

    fun execute() : WeatherInfo? {
        val forecastJsonStr = URL(URL+ townID).readText()
        Log.i("WeatherRequest","forecastJsonStr="+forecastJsonStr)
        try {
            val response =  Gson().fromJson(forecastJsonStr, WeatherResponse::class.java)
            return ForecastDataMapper().convertFromDataResult(response)
        }catch (e: Exception){
            e.printStackTrace()
            try {
                val response =  Gson().fromJson(forecastJsonStr, WeatherResponseBak::class.java)
                return ForecastDataMapper().convertFromDataResult(response)
            }catch (e: Exception){
                e.printStackTrace()
                return null
            }

        }
    }
}