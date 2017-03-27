package com.tv.forecast.domain.commands

import com.tv.forecast.data.WeatherRequest
import com.tv.forecast.domain.model.WeatherInfo

class RequestWeatherCommand(val townCode: String) : Command<WeatherInfo> {
    override fun execute(): WeatherInfo? {
        return WeatherRequest(townCode).execute()
    }
}