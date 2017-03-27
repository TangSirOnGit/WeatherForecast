package com.tv.forecast.data.db

import com.tv.forecast.domain.mappers.ForecastDataMapper
import com.tv.forecast.domain.model.City
import com.tv.forecast.extensions.parseList
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(val dbHelper: ForecastDbHelper = ForecastDbHelper.instance){
    fun getSavedCity() = dbHelper.use {
        val savedCity = select(SavedCityTable.NAME)
                .parseList { CitySavedInDb(HashMap(it)) }

        savedCity.map { ForecastDataMapper().convertCityFromDb(it) }
    }

    fun addCityToSavedTable(city: City) = dbHelper.use {
        insert(SavedCityTable.NAME,
                SavedCityTable.TOWNID to city.id,
                SavedCityTable.TOWNNAME to city.townName,
                SavedCityTable.TOWNNAMEEN to city.townNameEn)

    }

    fun deleteCityFromSavedTable(city: City)=dbHelper.use {
        delete(SavedCityTable.NAME,"townID = ?", arrayOf(city.id))
    }

    fun queryCityByName(cityName: String) = dbHelper.use {
        val cityRequest = "${CityCodeTable.TOWNENABB} = ? OR lower(${CityCodeTable.TOWNEN}) = ? OR ${CityCodeTable.TOWNNAME} = ?"
        val cityResult = select(CityCodeTable.NAME)
                .whereSimple(cityRequest, cityName,cityName,cityName)
                .parseList { CityCodeInfo(HashMap(it)) }
        cityResult.map { ForecastDataMapper().convertCityFromDb(it) }
    }
}