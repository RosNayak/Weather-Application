package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.repository.UserActionsRepository
import com.example.weatherapp.usecase.CitySearchHistoryUsecase
import kotlinx.android.synthetic.main.fragment_enter_city.*

class EnterCityFragment : Fragment() {

    private lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enter_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        submit.setOnClickListener(submitButtonListener)
        clearSearchHistory.setOnClickListener(clearSearchHistoryListener)
    }

    override fun onStart() {
        super.onStart()
        setEditTextAdapter()
    }

    private fun setEditTextAdapter() {
        val searchHistoryData = CitySearchHistoryUsecase().getSearchCityHistory()
        val adapter = ArrayAdapter<String>(context as Context, R.layout.adapter_layout, searchHistoryData)
        enterCity.setAdapter(adapter)
    }

    private val submitButtonListener = View.OnClickListener {
        val cityEntered : String = enterCity.text.toString()

        if (cityEntered == "") {
            enterCity.error = "City cant be blank."
        } else {
            val bundle : Bundle = bundleOf("cityName" to cityEntered)
            navController.navigate(R.id.action_enterCityFragment_to_todayWeatherFragment, bundle)
        }
    }

    private val clearSearchHistoryListener = View.OnClickListener {
        UserActionsRepository().clearSearchHistory()
        setEditTextAdapter()
        Toast.makeText(requireActivity(), "Search history cleared.", Toast.LENGTH_SHORT).show()
    }
}