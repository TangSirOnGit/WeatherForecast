package com.tv.forecast.data

data class WeatherResponse(val status: String,var weather: List<WeatherBean>)

data class WeatherBean(val city_name: String,
                       val city_id: String,
                       val last_update: String,
                       val now: WeatherNow,
                       val today: TodayBean,
                       val future: List<FutureBean>)

data class WeatherNow(
    val text: String,
    val code: String,
    val temperature: String,
    val feels_like: String,
    val wind_direction: String,
    val wind_speed: String,
    val wind_scale: String,
    val humidity: String,
    val visibility: String,
    val pressure: String,
    val pressure_rising: String,
    val air_quality: AirQualityBean,
    val alarms: List<String>)

data class AirQualityBean(
    val city: CityAirQuality,
    val stations: Any)

data class CityAirQuality(
    val aqi: String,
    val pm25: String,
    val pm10: String,
    val so2: String,
    val no2: String,
    val co: String,
    val o3: String,
    val last_update: String,
    val quality: String)

data class FutureBean(
    val date: String,
    val high: String,
    val low: String,
    val day: String,
    val text: String,
    val code1: String,
    val code2: String,
    val cop: String,
    val wind: String)

data class TodayBean(val sunrise: String,
                val sunset: String,
                val suggestion: LifeSuggestion)

data class LifeSuggestion(val dressing: SuggestionBean,
                          val uv: SuggestionBean,
                          val car_washing: SuggestionBean,
                          val travel: SuggestionBean,
                          val flu: SuggestionBean,
                          val sport:SuggestionBean)

data class SuggestionBean(val brief: String, val details: String)


/**
 * status : OK
 * hourly : [{"text":"多云","code":"4","temperature":"0","time":"2017-03-20T21:00:00+08:00"},{"text":"多云","code":"4","temperature":"0","time":"2017-03-20T22:00:00+08:00"},{"text":"多云","code":"4","temperature":"-1","time":"2017-03-20T23:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T00:00:00+08:00"},{"text":"多云","code":"4","temperature":"-5","time":"2017-03-21T01:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T02:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T03:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T04:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T05:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T06:00:00+08:00"},{"text":"多云","code":"4","temperature":"-2","time":"2017-03-21T07:00:00+08:00"},{"text":"多云","code":"4","temperature":"-1","time":"2017-03-21T08:00:00+08:00"},{"text":"多云","code":"4","temperature":"0","time":"2017-03-21T09:00:00+08:00"},{"text":"多云","code":"4","temperature":"1","time":"2017-03-21T10:00:00+08:00"},{"text":"多云","code":"4","temperature":"2","time":"2017-03-21T11:00:00+08:00"},{"text":"多云","code":"4","temperature":"6","time":"2017-03-21T12:00:00+08:00"},{"text":"多云","code":"4","temperature":"2","time":"2017-03-21T13:00:00+08:00"},{"text":"多云","code":"4","temperature":"2","time":"2017-03-21T14:00:00+08:00"},{"text":"多云","code":"4","temperature":"2","time":"2017-03-21T15:00:00+08:00"},{"text":"多云","code":"4","temperature":"1","time":"2017-03-21T16:00:00+08:00"},{"text":"晴","code":"0","temperature":"1","time":"2017-03-21T17:00:00+08:00"},{"text":"晴","code":"0","temperature":"1","time":"2017-03-21T18:00:00+08:00"},{"text":"晴","code":"0","temperature":"0","time":"2017-03-21T19:00:00+08:00"},{"text":"多云","code":"4","temperature":"-1","time":"2017-03-21T20:00:00+08:00"}]
 */
data class HourlyForeCastResponse(
    val status: String,
    val hourly: List<HourlyBean>)

/**
 * text : 多云
 * code : 4
 * temperature : 0
 * time : 2017-03-20T21:00:00+08:00
 */
data class HourlyBean(
    val text: String,
    val code: String,
    val temperature: String,
    val time: String)