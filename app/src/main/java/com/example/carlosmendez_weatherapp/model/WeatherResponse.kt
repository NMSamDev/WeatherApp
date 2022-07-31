package com.example.carlosmendez_weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherResponse(
    val list: List<WeatherListItem>,
    val city: CityW
)

@Parcelize
data class WeatherListItem(
    val main: MainW,
    val weather: List<WeatherItemW>,
    val clouds: CloudsW,
    val wind: WindW,
    val sys: SysW,
    val dt_txt: String,
    val visibility: Int,
): Parcelable

@Parcelize
data class MainW(
    val temp: Float, // It's in Kelvin
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Float
): Parcelable

@Parcelize
data class WeatherItemW(
    val main: String,
    val description: String,
    val icon: String
): Parcelable

@Parcelize
data class CloudsW(
    val all: Int
): Parcelable

@Parcelize
data class WindW(
    val speed: Float,
    val deg: Int,
    val gust: Float
): Parcelable

@Parcelize
data class SysW(
    val pod: String
): Parcelable

@Parcelize
data class CityW(
    val name: String,
    val country: String,
    val coord: CoordCity
): Parcelable

@Parcelize
data class CoordCity(
    val lat: Float,
    val lon: Float
): Parcelable

