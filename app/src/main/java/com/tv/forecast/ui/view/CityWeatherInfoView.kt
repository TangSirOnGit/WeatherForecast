package com.tv.forecast.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.tv.forecast.R
import com.tv.forecast.domain.commands.RequestDetailCommand
import com.tv.forecast.domain.commands.RequestWeatherCommand
import com.tv.forecast.domain.model.AirQuality
import com.tv.forecast.domain.model.HourlyForeCast
import com.tv.forecast.domain.model.Suggestion
import com.tv.forecast.domain.model.WeatherInfo
import com.tv.forecast.event.WeatherUpdatedEvent
import com.tv.forecast.ui.adapter.DailyForecastAdapter
import com.tv.forecast.ui.adapter.HourlyForecastAdapter
import com.tv.forecast.ui.adapter.WeatherDetailAdapter
import com.tv.forecast.utils.TimeUtils
import kotlinx.android.synthetic.main.city_weather_info_layout.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CityWeatherInfoView(context: Context, val cityAreaCode: String) : RelativeLayout(context) {
    init {
        init(null, 0)
    }

    val TAG = "CityWeatherInfoView"

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.city_weather_info_layout, this, true)
        dailyForeCast.layoutManager = LinearLayoutManager(context)
        todaySummary.layoutManager = LinearLayoutManager(context)
        todayAirQuality.layoutManager = LinearLayoutManager(context)
        errorHint.visibility = View.INVISIBLE
    }

    fun updateWeatherInfo() {
        doAsync {
            val weather = RequestWeatherCommand(cityAreaCode).execute()
            val hourly = RequestDetailCommand(cityAreaCode).execute()
            uiThread {
                if (weather != null && hourly != null) {
                    noticeWeatherUpdated(weather.weatherCode.toInt())
                    updateWeatherView(weather)
                    updateHourlyForecast(hourly)
                } else {
                    noticeWeatherUpdated(99)
                    showErrorInfo()
                }
            }
        }
    }
    fun convertForecastToNow(info: HourlyForeCast) = HourlyForeCast(
            time = context.getString(R.string.hourly_forecast_now),
            code = info.code,
            hour = info.hour,
            temperature = info.temperature,
            text = info.text
    )

    fun updateHourlyForecast(hourlyList: List<HourlyForeCast>) {
        hourlyForecast.setNumRows(1)
        val hourNow = TimeUtils.getCurrentHour()
        val firstIndex = hourlyList.indexOfFirst { it.hour >=hourNow }
        val forecastNow = convertForecastToNow(hourlyList[firstIndex])
        hourlyForecast.adapter = HourlyForecastAdapter(listOf(forecastNow) +
                hourlyList.slice(firstIndex+1..hourlyList.size-1))
    }

    fun updateSuggestion(list: List<Suggestion>) {
        val strList = list.map { it.detail }
        todaySuggestion.start(strList)
    }

    fun updateSummary(weather: WeatherInfo) {
        with(weather)
        {
            val summaryList = listOf(
                    String.format(context.getString(R.string.sun_rise_time), sunrise),
                    String.format(context.getString(R.string.sun_set_time), sunset),
                    String.format(context.getString(R.string.wind_info), windDirection, windSpeed),
                    String.format(context.getString(R.string.humidity), humidity),
                    String.format(context.getString(R.string.feel_like), feelLike),
                    String.format(context.getString(R.string.visibility), visibility)
            )

            todaySummary.adapter = WeatherDetailAdapter(summaryList)
        }

    }

    fun updateAirQuality(air: AirQuality) {
        with(air) {
            val airQualityList = listOf(
                    String.format(context.getString(R.string.air_quality), quality),
                    String.format(context.getString(R.string.air_quality_aqi), aqi),
                    String.format(context.getString(R.string.air_quality_pm25), pm25),
                    String.format(context.getString(R.string.air_quality_pm10), pm10),
                    String.format(context.getString(R.string.air_quality_so2), so2),
                    String.format(context.getString(R.string.air_quality_no2), no2),
                    String.format(context.getString(R.string.air_quality_co), co),
                    String.format(context.getString(R.string.air_quality_o3), o3)
            )
            todayAirQuality.adapter = WeatherDetailAdapter(airQualityList)
        }
    }

    fun showErrorInfo() {
        weatherContent.visibility = View.INVISIBLE
        errorHint.visibility = View.VISIBLE
    }

    fun noticeWeatherUpdated(weatherCode: Int) {
        EventBus.getDefault().post(WeatherUpdatedEvent(cityCode = cityAreaCode, weatherCode = weatherCode))
    }

    fun updateWeatherView(weather: WeatherInfo) {
        Log.i(TAG, "updateWeatherView")
        with(weather) {
            headCityName.text = cityName
            headDateInfo.text = String.format(
                    context.getString(R.string.date_for_today), date)
            currentTemperature.text = String.format(
                    context.getString(R.string.current_temperature), temperature)
            currentWeatherType.text = weatherType
            maxTemp.text = foreCast[0].tempMax
            minTemp.text = foreCast[0].tempMin
            dailyForeCast.adapter = DailyForecastAdapter(foreCast.slice(1..8))
            updateSuggestion(suggestion)
        }
        updateSummary(weather)
        updateAirQuality(weather.airQuality)
    }
}
