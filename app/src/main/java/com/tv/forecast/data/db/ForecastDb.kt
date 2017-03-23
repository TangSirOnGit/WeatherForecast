package com.tv.forecast.data.db

import android.util.Log
import com.tv.forecast.domain.mappers.ForecastDataMapper
import com.tv.forecast.domain.model.City
import com.tv.forecast.extensions.parseList
import com.tv.forecast.extensions.parseOpt
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by tangsir on 2017/3/25.
 */

class ForecastDb(val dbHelper: ForecastDbHelper = ForecastDbHelper.instance){
    fun getSavedCity() = dbHelper.use {
        val savedCity = select(SavedCityTable.NAME)
                .parseList { CitySavedInDb(HashMap(it)) }
        //return savedCity
        Log.i("test tag", "getSavedCity,savedCity size="+savedCity.size)

        savedCity.map { ForecastDataMapper().convertCityFromDb(it) }
    }

    fun addCityToSavedTable(city: City) = dbHelper.use {
        Log.i("search","addCityToSavedTable, city=" +city)
        insert(SavedCityTable.NAME,
                SavedCityTable.TOWNID to city.id,
                SavedCityTable.TOWNNAME to city.townName,
                SavedCityTable.TOWNNAMEEN to city.townNameEn)

    }

    fun deleteCityFromSavedTable(city: City)=dbHelper.use {
        delete(SavedCityTable.NAME,"townID = ?", arrayOf(city.id))
    }

    fun queryCityByName(cityName: String) = dbHelper.use {
        Log.i("test tag", "queryCityByName,cityName "+cityName)

        val cityRequest = "${CityCodeTable.TOWNENABB} = ?"
        val cityResult = select(CityCodeTable.NAME)
                .whereSimple(cityRequest, cityName)
                .parseList { CityCodeInfo(HashMap(it)) }
        Log.i("test tag", "queryCityByName,cityResult size="+cityResult.size)

        cityResult.map { ForecastDataMapper().convertCityFromDb(it) }
    }
}