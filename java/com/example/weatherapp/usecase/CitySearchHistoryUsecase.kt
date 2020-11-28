package com.example.weatherapp.usecase

import com.example.weatherapp.repository.UserActionsRepository

class CitySearchHistoryUsecase {

    fun getSearchCityHistory() : ArrayList<String> {
        return UserActionsRepository().getSearchHistory()
    }

    fun addCityToSearchHistory(newCity : String) {
        UserActionsRepository().updateSearchHistory(newCity)
    }
}