package com.tv.forecast.domain.mappers

import android.util.Log
import com.tv.forecast.data.*
import com.tv.forecast.data.db.CityCodeInfo
import com.tv.forecast.data.db.CitySavedInDb
import com.tv.forecast.domain.model.*


class ForecastDataMapper {
    fun convertFromDataResult(weatherResult: WeatherResponse?): WeatherInfo? {
        Log.i("test-tag","weatherResult="+ weatherResult)
        val weather = weatherResult?.weather?.get(0) ?: return null
        with(weather){
            return WeatherInfo(
                    cityName = city_name,
                    updateTime = last_update,
                    date = future[0].day,
                    weatherType = now.text,
                    weatherCode = now.code,
                    windDirection = now.wind_direction,
                    windSpeed = now.wind_speed,
                    humidity = now.humidity,
                    temperature = now.temperature,
                    sunrise = today.sunrise,
                    sunset = today.sunset,
                    visibility = now.visibility,
                    feelLike = now.feels_like,
                    airQuality = convertAirQualityFromBean(now.air_quality.city),
                    foreCast = convertFromWeatherBean(future),
                    suggestion = convertSuggestionFromBean(today.suggestion))
        }

    }

    fun convertFromDataResult(weatherResult: WeatherResponseBak?): WeatherInfo? {
        Log.i("test-tag","weatherResult="+ weatherResult)
        val weather = weatherResult?.weather?.get(0) ?: return null
        with(weather){
            return WeatherInfo(
                    cityName = city_name,
                    updateTime = last_update,
                    date = future[0].day,
                    weatherType = now.text,
                    weatherCode = now.code,
                    windDirection = now.wind_direction,
                    windSpeed = now.wind_speed,
                    humidity = now.humidity,
                    temperature = now.temperature,
                    sunrise = today.sunrise,
                    sunset = today.sunset,
                    visibility = now.visibility,
                    feelLike = now.feels_like,
                    airQuality = convertAirQualityFromBean(now.air_quality.city),
                    foreCast = convertFromWeatherBean(future),
                    suggestion = emptyList())
        }

    }

    fun convertAirQualityFromBean(bean: CityAirQuality): AirQuality{
        val noValue = "--"
        return AirQuality(
                quality = bean.quality?:noValue,
                so2 = bean.so2?:noValue,
                aqi = bean.aqi?:noValue,
                pm25 = bean.pm25?:noValue,
                pm10 = bean.pm10?:noValue,
                no2 = bean.no2?:noValue,
                o3 = bean.o3?:noValue,
                co = bean.co?:noValue,
                last_update = bean.last_update?:noValue
        )
    }
    fun convertSuggestionFromBean(bean: LifeSuggestion):List<Suggestion> {
       with(bean) {
           return listOf(
                Suggestion(type = 0, brief = dressing.brief, detail = dressing.details),
                Suggestion(type = 1, brief = flu.brief, detail = flu.details),
                Suggestion(type = 2, brief = car_washing.brief, detail = car_washing.details),
                Suggestion(type = 3, brief = travel.brief, detail = travel.details),
                Suggestion(type = 4, brief = sport.brief, detail = sport.details),
                Suggestion(type = 5, brief = uv.brief, detail = uv.details)
        )
    }
    }

    fun convertFromWeatherBean(list: List<FutureBean>):List<DayForeCastInfo>{
        return list.map { convertWeatherToDomain(it) }
    }

    fun convertWeatherToDomain(future: FutureBean): DayForeCastInfo {
        return DayForeCastInfo(date = future.date,
                day = future.day,
                text = future.text,
                tempMax = future.high,
                tempMin = future.low,
                weatherType1 = future.code1,
                weatherType2 = future.code2)
    }

    fun convertHourlyFromResponse(response: HourlyForeCastResponse?): List<HourlyForeCast>?{
        response?.hourly ?: return null
        return  response.hourly.map { hourlyBeanToForeCast(it) }
    }

    fun hourlyBeanToForeCast(bean: HourlyBean): HourlyForeCast{
        return HourlyForeCast(
                text = bean.text,
                code = bean.code.toInt(),
                temperature = bean.temperature,
                hour = getHour(bean.time)
                //time = bean.time
        )
    }

    /*2017-03-21T10:00:00+08:00*/
    fun getHour(time: String): Int{
        val indexT = time.indexOf("T")
        return time.slice(indexT+1..indexT+2).toInt()
    }

    fun convertCityFromDb(bean: CitySavedInDb): City {
        return City(
                id = bean.townID,
                townName = bean.townName,
                townNameEn = bean.townNameEn,
                type = 0
        )
    }

    fun convertCityFromDb(bean: CityCodeInfo): City {
        return City(
                id = bean.townID,
                townName = bean.townName,
                townNameEn = bean.townEnAbb,
                cityName = bean.cityName,
                type = 0
        )
    }

}