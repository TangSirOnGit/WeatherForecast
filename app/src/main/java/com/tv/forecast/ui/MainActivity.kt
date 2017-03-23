package com.tv.forecast.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log
import android.view.View
import com.tv.forecast.R
import com.tv.forecast.data.db.ForecastDb
import com.tv.forecast.event.WeatherUpdatedEvent
import com.tv.forecast.ui.adapter.CityViewPagerAdapter
import com.tv.forecast.ui.view.CityWeatherInfoView
import com.tv.forecast.utils.ImageResUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : Activity(), OnPageChangeListener {
    val TAG = "MainActivity"
    var cityViews: List<CityWeatherInfoView>? = null
    var currentSelectedPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateView()
        citiesViewPager.adapter = CityViewPagerAdapter(cityViews)
        citiesViewPager.addOnPageChangeListener(this)
        btnSetting.setOnClickListener { gotoSettingActivity() }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    fun updateView() {
        val cities = ForecastDb().getSavedCity()
        if (cities == null || cities.isEmpty()) {
            cityViews = emptyList()
            noCityHintMessage.visibility = View.VISIBLE
            updateBgByWeatherCode(99)
        } else {
            noCityHintMessage.visibility = View.INVISIBLE
            cityViews = cities.map { CityWeatherInfoView(context = this, cityAreaCode = it.id) }
            updateWeather(0)
        }
        pageIndicator.setPageNum(cityViews?.size ?:0)
        citiesViewPager.adapter = CityViewPagerAdapter(cityViews)
        if (cities.size > 1) {
            pageIndicator.updateIndicator(0)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        currentSelectedPage = position
        updateWeather(position)
    }

    fun updateWeather(position: Int) {
        pageIndicator.updateIndicator(position)
        cityViews?.get(position)?.updateWeatherInfo()
    }

    fun updateBgByWeatherCode(code: Int) {
        weatherMainPage.setBackgroundResource(ImageResUtils().getBgImageResId(code))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onWeatherUpdatedEvent(event: WeatherUpdatedEvent) {
        Log.i(TAG, "onWeatherUpdatedEvent,event.cityCode="+event.cityCode)
        if (event.cityCode == cityViews?.get(currentSelectedPage)?.cityAreaCode) {
            updateBgByWeatherCode(event.weatherCode)
        }
    }

    fun gotoSettingActivity() {
        val settingIntent = Intent()
        settingIntent.setClassName("com.tv.forecast", "com.tv.forecast.ui.CitySettingActivity")
        startActivityForResult(settingIntent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        updateView()
    }
}
