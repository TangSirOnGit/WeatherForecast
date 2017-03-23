package com.tv.forecast.event

class WeatherUpdatedEvent(val cityCode:String, val weatherCode: Int)

class SavedCityUpdatedEvent