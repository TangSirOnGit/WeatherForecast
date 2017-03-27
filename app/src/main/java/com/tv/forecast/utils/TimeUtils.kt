package com.tv.forecast.utils

import android.text.format.Time


object TimeUtils{
    fun getCurrentHour(): Int{
        val t = Time() // or Time t=new Time("GMT+8"); 加上Time Zone资料
        t.setToNow() // 取得系统时间。
        return t.hour
    }

}