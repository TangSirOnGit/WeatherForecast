package com.tv.forecast.ui.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.tv.forecast.R
import com.tv.forecast.data.db.ForecastDb
import com.tv.forecast.domain.model.City
import com.tv.forecast.event.SavedCityUpdatedEvent
import com.tv.forecast.ui.adapter.CityAdapter
import kotlinx.android.synthetic.main.city_search_view_layout.view.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * TODO: document your custom view class.
 */
class CitySearchView : RelativeLayout {
    val TAG = "CitySearchView"

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        LayoutInflater.from(context).inflate(R.layout.city_search_view_layout, this, true)
        searchResultList.layoutManager = LinearLayoutManager(context)
        searchInputBox.addTextChangedListener(textWatcher)
    }

    fun searchResultClicked(city: City) {
        Log.i("search", "searchResultClicked, city=" + city)
        ForecastDb().addCityToSavedTable(city)
        EventBus.getDefault().post(SavedCityUpdatedEvent())
    }

    fun queryCities(cityName: String) {
        doAsync {
            val cities = ForecastDb().queryCityByName(cityName)
            uiThread {
                if (cities == null || cities.isEmpty()) {
                    searchResultList.adapter = CityAdapter(emptyList<City>(), { searchResultClicked(it) })
                } else {
                    searchResultList.adapter = CityAdapter(cities, { searchResultClicked(it) })
                }
            }

        }
    }

    private val textWatcher = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {
            // TODO Auto-generated method stub
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                       after: Int) {
            // TODO Auto-generated method stub
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                   count: Int) {
            val str = searchInputBox.getText().toString()
            try {
                Log.d("TAG", "onTextChanged--------------->,str=" + str)
                queryCities(str)

            } catch (e: Exception) {
                // TODO: handle exception

            }

        }
    }

}
