package com.example.weatherapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.entity.WeatherWithDataAndTime
import kotlinx.android.synthetic.main.recycler_view_layout.view.*

class WeatherHistoryRecyclerView(
    private val context: Context,
    private val weatherData : List<WeatherWithDataAndTime>
) : RecyclerView.Adapter<WeatherHistoryRecyclerView.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateView : TextView by lazy { itemView.date }
        val timeView : TextView by lazy { itemView.time }
        val temperatureView : TextView by lazy { itemView.instantTemperature }
        val descriptionView : TextView by lazy { itemView.instantdescription }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        return RecyclerViewHolder(inflater.inflate(R.layout.recycler_view_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentData = weatherData[position]
        val tempStringList : List<String> = currentData.dateTime.split(' ')

        holder.dateView.text = tempStringList[0]
        holder.timeView.text = tempStringList[1]
        val modifyTemp = (currentData?.temperatureData?.temperature?.toInt()
            ?.minus(273)).toString() + " 'C"
        holder.temperatureView.text = modifyTemp
        holder.descriptionView.text = currentData.weather[0].description
    }
}