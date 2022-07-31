package com.example.carlosmendez_weatherapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carlosmendez_weatherapp.databinding.WeatherListItemBinding
import com.example.carlosmendez_weatherapp.model.WeatherListItem
import java.time.LocalDateTime
import java.time.LocalTime

private const val TAG = "Adapter"

class WeatherAdapter(
    private val list: MutableList<WeatherListItem> = mutableListOf()
): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(private val binding: WeatherListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun onBind(item: WeatherListItem) {
                binding.apply {
                    Log.d(TAG, "onBind:${item.dt_txt}")
                    tvDate.text = formatDate(item.dt_txt)
                    tvMeasure.text = tempMeasure
                    tvTemperature.text = (item.main.temp.toInt()).toString()
                    tvWeatherDesc.text = item.weather.first().description
                    val iconUrl = "https://openweathermap.org/img/wn/${item.weather.first().icon}@4x.png"
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

    private fun formatDate(dateOld: String): String {
        val dateNew = LocalDateTime.parse(dateOld.replace(" ", "T"))
//        Log.d(TAG, "formatDate: $dateNew")

        return dateNew.hour.toString()+":00 "+ dateNew.month.toString() + " " + dateNew.dayOfMonth.toString() + " "+dateNew.dayOfWeek.toString()
    }

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