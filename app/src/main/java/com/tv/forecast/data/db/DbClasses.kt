package com.tv.forecast.data.db

class CitySavedInDb (var map: MutableMap<String, Any?>) {
    init {

    }
    var townID: String by map
    var townName: String by map
    var townNameEn: String by map

    constructor(townID: String, townName: String, townNameEn: String)
            : this(HashMap()) {
        this.townID = townID
        this.townName = townName
        this.townNameEn = townNameEn
    }
}

class CityCodeInfo(var map: MutableMap<String, Any?>) {
    var ID: String by map
    var cityName: String by map
    var cityEN: String by map
    var townID: String by map
    var townName: String by map
    var townEN: String by map
    var townEnAbb: String by map


    constructor(ID: String, cityName: String, cityEN: String,
                townID: String, townName: String, townEN: String, townEnAbb: String)
            : this(HashMap()) {
        this.ID = ID
        this.cityName = cityName
        this.townID = townID
        this.cityEN = cityEN
        this.townName = townName
        this.townEN = townEN
        this.townEnAbb = townEnAbb
    }
}