package com.tv.forecast.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tv.forecast.R
import com.tv.forecast.domain.model.DayForeCastInfo
import com.tv.forecast.utils.ImageResUtils
import kotlinx.android.synthetic.main.daily_forecast_info_layout.view.*

class DailyForecastAdapter(val items:List<DayForeCastInfo>):
        RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.daily_forecast_info_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bindForecast(forecast: DayForeCastInfo) {
            Log.i("DailyForecastAdapter","ViewHolder,bindInfo,forecast="+forecast)
            with(forecast) {
                view.dailyDay.text = forecast.day
                view.dailyImage.setImageResource(
                        ImageResUtils().getImageResId(forecast.weatherType1.toInt())
                )
                view.dailyTempMax.text = forecast.tempMax
                view.dailyTempMin.text = forecast.tempMin
            }
        }
    }
}