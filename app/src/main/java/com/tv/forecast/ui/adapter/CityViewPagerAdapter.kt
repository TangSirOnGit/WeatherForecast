package com.tv.forecast.ui.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.tv.forecast.ui.view.CityWeatherInfoView


class CityViewPagerAdapter(val cityViews: List<CityWeatherInfoView>?): PagerAdapter() {
    override fun isViewFromObject(arg0: View?, arg1: Any?): Boolean {
        return arg0 == arg1
    }

    override fun getCount() = cityViews?.size ?: 0

    override fun instantiateItem(container: ViewGroup?, position: Int): CityWeatherInfoView? {
        container?.addView(cityViews?.get(position))
        return cityViews?.get(position)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(cityViews?.get(position))
    }
}