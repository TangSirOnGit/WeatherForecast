package com.tv.forecast.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tv.forecast.R
import com.tv.forecast.domain.model.DayForeCastInfo
import com.tv.forecast.domain.model.City
import com.tv.forecast.utils.ImageResUtils
import kotlinx.android.synthetic.main.city_info_layout.view.*
import kotlinx.android.synthetic.main.daily_forecast_info_layout.view.*

class CityAdapter(var items:List<City>, val itemClick: (City) -> Unit):
        RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.city_info_layout, parent, false)
        return ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateCityList(cityList:List<City>){
        items = cityList
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View,val itemClick: (City) -> Unit) : RecyclerView.ViewHolder(view){
        fun bindForecast(city: City) {
            Log.i("CityAdapter","ViewHolder,bindInfo,city="+city)
            with(city) {
                view.townName.text = townName
                if (cityName.isNotEmpty()) {
                    val name = String.format(
                            view.cityName.context.getString(R.string.city_name_with_brace),
                            cityName)
                    view.cityName.text = name
                }

                view.setOnClickListener { itemClick(this) }
            }
        }
    }
}