package com.tv.forecast.domain.commands

import com.tv.forecast.data.WeatherRequest
import com.tv.forecast.domain.mappers.ForecastDataMapper
import com.tv.forecast.domain.model.WeatherInfo

class RequestWeatherCommand(val areaCode: String) : Command<WeatherInfo> {
    override fun execute(): WeatherInfo? {
        return WeatherRequest(areaCode).execute()
    }
}