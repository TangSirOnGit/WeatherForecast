package com.tv.forecast.domain.model

data class WeatherInfo(val cityName: String,
                       val updateTime: String,
                       val date: String,
                       val weatherType: String,
                       val weatherCode: String,
                       val temperature: String,
                       val windSpeed: String,
                       val windDirection: String,
                       val humidity: String,
                       val visibility: String,
                       val sunrise: String,
                       val sunset: String,
                       val feelLike: String,
                       val airQuality: AirQuality,
                       val foreCast: List<DayForeCastInfo>,
                       val suggestion: List<Suggestion>)
data class DayForeCastInfo(val date: String,
                           val day: String,
                           val text: String,
                           val tempMax: String,
                           val tempMin: String,
                           val weatherType1: String,
                           val weatherType2: String)

data class HourlyForeCast(
        val text: String,
        val code: Int,
        val temperature: String,
        //val time: String,
        val hour: Int)
data class Suggestion(
        val type: Int,
        val brief: String,
        val detail: String)

data class AirQuality(
        val aqi: String,
        val pm25: String,
        val pm10: String,
        val so2: String,
        val no2: String,
        val co: String,
        val o3: String,
        val last_update: String,
        val quality: String)

data class City(val id: String,
                val cityName: String = "",
                val townName:String,
                val townNameEn: String,
                val type:Int)