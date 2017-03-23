package com.tv.forecast.data

data class WeatherResponseBak(val status: String,var weather: List<WeatherBeanBak>)

data class WeatherBeanBak(val city_name: String,
                       val city_id: String,
                       val last_update: String,
                       val now: WeatherNow,
                       val today: TodayBeanBak,
                       val future: List<FutureBean>)

data class TodayBeanBak(val sunrise: String,
                val sunset: String,
                val suggestion: LifeSuggestionBak)

data class LifeSuggestionBak(val dressing: Boolean,
                          val uv: Boolean,
                          val car_washing: Boolean,
                          val travel: Boolean,
                          val flu: Boolean,
                          val sport:Boolean)


