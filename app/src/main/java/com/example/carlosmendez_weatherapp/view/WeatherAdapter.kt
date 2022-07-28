package com.example.carlosmendez_weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_weatherapp.databinding.WeatherListItemBinding
import com.example.carlosmendez_weatherapp.model.WeatherListItem

class WeatherAdapter(
    private val list: MutableList<WeatherListItem> = mutableListOf()
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    enum class Measure{
        KELVIN,
        CELSIUS,
        FAHRENHEIT
    }

    private var tempMeasure: String = " K"

    inner class WeatherViewHolder(private val binding: WeatherListItemBinding)
        :RecyclerView.ViewHolder(binding.root) {
            fun onBind(item: WeatherListItem) {
                binding.apply {
                    tvDate.text = item.dt_txt
                    tvMeasure.text = tempMeasure
                    tvTemperature.text = (item.main.temp.toInt()).toString()
                    tvWeatherDesc.text = item.weather.description
                    Glide.with(ivWeatherIcon)
                        .load("http://openweathermap.org/img/wn/${item.weather.icon}@2x.png")
                        .into(ivWeatherIcon)

                }
            }
        }

    private fun formatDate(date: String) {

    }

    fun setWeatherList(newList: List<WeatherListItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
    fun changeMeasure(selected: Measure) {
        tempMeasure = when(selected) {
            Measure.KELVIN -> " K"
            Measure.CELSIUS -> " °C"
            Measure.FAHRENHEIT -> " °F"
        }
    }
    // °F
    // (K - 273.15) * 1.8 + 32

    // °C
    // K - 273.15

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
}