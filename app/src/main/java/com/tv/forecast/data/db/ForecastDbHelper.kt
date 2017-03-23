package com.tv.forecast.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.tv.forecast.App
import com.tv.forecast.utils.FileUtils
import org.jetbrains.anko.db.*

/**
 * Created by tangsir on 2017/3/25.
 */
class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = FileUtils.dataBaseName
        val DB_VERSION = 2
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(SavedCityTable.NAME, true,
                SavedCityTable.TOWNID to TEXT + PRIMARY_KEY,
                SavedCityTable.TOWNNAME to TEXT,
                SavedCityTable.TOWNNAMEEN to TEXT)
        db.createTable(CityCodeTable.NAME,true,
                CityCodeTable.ID to TEXT + PRIMARY_KEY,
                CityCodeTable.CITYNAME to TEXT,
                CityCodeTable.CITYEN to TEXT,
                CityCodeTable.TOWNID to TEXT,
                CityCodeTable.TOWNNAME to TEXT,
                CityCodeTable.TOWNEN to TEXT,
                CityCodeTable.TOWNENABB to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(SavedCityTable.NAME, true)
        db.dropTable(CityCodeTable.NAME,true)
        onCreate(db)
    }
}