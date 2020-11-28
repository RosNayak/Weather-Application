package com.example.weatherapp.repository

import com.example.weatherapp.entity.SearchedCities
import io.realm.Realm
import java.util.ArrayList

class UserActionsRepository {

    fun updateSearchHistory(newSearch : String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            val newSearchCity = it.createObject(SearchedCities::class.java)
            newSearchCity.searchedCity = newSearch
        }
        realm.close()
    }

    fun getSearchHistory() : ArrayList<String> {
        val realm = Realm.getDefaultInstance()
        val searchHistory = realm.where(SearchedCities::class.java).findAll()
        val result = ArrayList<String>()
        for (city in searchHistory) {
            result.add(city.searchedCity.toString())
        }
        realm.close()
        return result
    }

    fun clearSearchHistory() {
        val realm = Realm.getDefaultInstance()
        val searchHistory = realm.where(SearchedCities::class.java).findAll()
        realm.executeTransaction {
            searchHistory.deleteAllFromRealm()
        }
        realm.close()
    }

}