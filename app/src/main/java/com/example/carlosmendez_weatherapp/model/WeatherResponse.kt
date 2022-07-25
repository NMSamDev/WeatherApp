package com.example.carlosmendez_weatherapp.model

data class WeatherResponse(
    val list: List<WeatherListItem>
)

data class WeatherListItem(
    val main: MainW,
    val weather: WeatherItemW,
    val clouds: CloudsW,
    val wind: WindW,
    val sys: SysW,
    val dt_txt: String,
    val visibility: Int,
    val city: CityW
)

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
)

data class WeatherItemW(
    val main: String,
    val description: String,
    val icon: String
)

data class CloudsW(
    val all: Int
)

data class WindW(
    val speed: Float,
    val deg: Int,
    val gust: Float
)

data class SysW(
    val pod: String
)

data class CityW(
    val name: String,
    val country: String,
    val coord: CoordCity
)

data class CoordCity(
    val lat: Float,
    val lon: Float
)

