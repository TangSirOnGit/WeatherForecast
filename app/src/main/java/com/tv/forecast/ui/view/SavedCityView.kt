package com.tv.forecast.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.tv.forecast.R
import com.tv.forecast.data.db.ForecastDb
import com.tv.forecast.domain.model.City
import com.tv.forecast.event.SavedCityUpdatedEvent
import com.tv.forecast.ui.adapter.CityAdapter
import kotlinx.android.synthetic.main.saved_city_view_layout.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * TODO: document your custom view class.
 */
class SavedCityView : RelativeLayout {
    val TAG = "SavedCityView"

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }


    fun cityItemClicked(city: City){
        Log.i("SavedCityView","cityItemClicked, city="+city)
        ForecastDb().deleteCityFromSavedTable(city)
        EventBus.getDefault().post(SavedCityUpdatedEvent())
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.saved_city_view_layout, this, true)
        savedCityList.layoutManager = LinearLayoutManager(context)
        updateSavedCityList()
    }

    fun updateSavedCityList(){
        doAsync{
            val savedCity = ForecastDb().getSavedCity()
            uiThread{
                savedCityList.adapter = CityAdapter(savedCity,{cityItemClicked(it)})
            }
        }
    }

}
