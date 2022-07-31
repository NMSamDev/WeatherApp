package com.example.carlosmendez_weatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_weatherapp.databinding.WeatherListItemBinding
import com.example.carlosmendez_weatherapp.model.WeatherListItem
import com.squareup.picasso.Picasso

private const val TAG = "Adapter"

class WeatherAdapter(
    private val list: MutableList<WeatherListItem> = mutableListOf()
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var city: String = ""
    var country: String = ""

    inner class WeatherViewHolder(private val binding: WeatherListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(item: WeatherListItem) {
                binding.apply {
                    Log.d(TAG, "onBind:${item.dt_txt}")
                    tvDate.text = item.dt_txt
                    tvMeasure.text = tempMeasure
                    tvTemperature.text = (item.main.temp.toInt()).toString()
                    tvWeatherDesc.text = item.weather.first().description
                    //city = item.city.name
//                    country = item.city.country
//
                    val iconUrl = "http://openweathermap.org/img/wn/${item.weather.first().icon}.png"
                    Log.d(TAG, "onBind:$iconUrl")

                    Glide.with(ivWeatherIcon)
                        .load(iconUrl)
                        .into(ivWeatherIcon)

//                    Picasso.get()
//                        .load(iconUrl)
//                        .fit()
//                        .into(ivWeatherIcon)
                }
            }
        }

    enum class Measure{
        KELVIN,
        CELSIUS,
        FAHRENHEIT
    }

    private var tempMeasure: String = " K"

    fun setWeatherList(newList: List<WeatherListItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

//    private fun formatDate(date: String): String {
//        return date + " date"
//    }

    fun changeMeasure(selected: Measure) {
        tempMeasure = when(selected) {
            Measure.KELVIN -> " K"
            Measure.CELSIUS -> " °C"
            Measure.FAHRENHEIT -> " °F"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    // °F
    // (K - 273.15) * 1.8 + 32

    // °C
    // K - 273.15

}