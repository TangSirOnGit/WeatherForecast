package com.tv.forecast.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tv.forecast.R
import com.tv.forecast.event.SavedCityUpdatedEvent
import kotlinx.android.synthetic.main.activity_setting.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by tangsir on 2017/3/25.
 */
class CitySettingActivity: Activity() {
    val TAG = "CitySettingActivity"
    var cityUpdated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        //savedCityView.requestFocus()
        citySearchView.requestFocus()
        updateResultIntent()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }



    fun updateResultIntent(){
        val resultIntent = Intent()
        resultIntent.putExtra("updated",cityUpdated)
        setResult(0 ,resultIntent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSavedCityUpdatedEvent(event: SavedCityUpdatedEvent) {
        cityUpdated = true
        updateResultIntent()
        savedCityView.updateSavedCityList()
    }
}