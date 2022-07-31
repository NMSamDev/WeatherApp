package com.example.carlosmendez_weatherapp.api

import com.example.carlosmendez_weatherapp.BuildConfig
import com.example.carlosmendez_weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    // https://api.openweathermap.org/data/2.5/forecast?q=Atlanta&appid=apikey
    @GET("data/2.5/forecast")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apikey: String = BuildConfig.APIKEY
    ) : Response<WeatherResponse>
}