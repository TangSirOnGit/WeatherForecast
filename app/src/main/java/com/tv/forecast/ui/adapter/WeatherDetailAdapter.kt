package com.tv.forecast.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tv.forecast.R
import kotlinx.android.synthetic.main.today_summary_info_layout.view.*

class WeatherDetailAdapter(val items:List<String>):
        RecyclerView.Adapter<WeatherDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.today_summary_info_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindInfo( items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bindInfo(info: String) {
            view.summaryItem.text = info
        }
    }
}