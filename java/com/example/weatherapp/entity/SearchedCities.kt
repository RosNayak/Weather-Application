package com.example.weatherapp.entity

import io.realm.RealmObject

open class SearchedCities(
    var searchedCity : String? = ""
) : RealmObject()