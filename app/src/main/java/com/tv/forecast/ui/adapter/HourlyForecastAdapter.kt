package com.tv.forecast.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tv.forecast.R
import com.tv.forecast.domain.model.HourlyForeCast
import com.tv.forecast.utils.ImageResUtils
import kotlinx.android.synthetic.main.hourly_forecast_info_layout.view.*

class HourlyForecastAdapter(val items:List<HourlyForeCast>):
        RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hourly_forecast_info_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bindForecast(forecast: HourlyForeCast) {
            with(forecast) {
                view.hourlyTitle.text = String.format(
                        view.context.getString(R.string.hour),forecast.hour
                )
                view.hourlyImage.setImageResource(
                        ImageResUtils().getImageResId(forecast.code))
                view.hourlyMessage.text = String.format(
                        view.context.getString(R.string.hourly_temperature),
                        forecast.temperature
                )
            }
        }
    }
}