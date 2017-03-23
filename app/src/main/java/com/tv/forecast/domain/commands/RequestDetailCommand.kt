package com.tv.forecast.domain.commands

import com.tv.forecast.data.HourlyWeatherRequest
import com.tv.forecast.domain.mappers.ForecastDataMapper
import com.tv.forecast.domain.model.HourlyForeCast

class RequestDetailCommand(val areaCode: String) : Command<List<HourlyForeCast>?> {
    override fun execute(): List<HourlyForeCast>? {
        val detailRequest = HourlyWeatherRequest(areaCode)
        return ForecastDataMapper().convertHourlyFromResponse(detailRequest.execute())
    }
}