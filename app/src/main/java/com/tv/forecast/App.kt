package com.tv.forecast

import android.app.Application
import com.tv.forecast.utils.FileUtils

class App : Application(){
    companion object{
        lateinit var instance:App
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        FileUtils().importDatabase(instance)
    }
}