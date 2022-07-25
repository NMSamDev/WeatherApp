package com.example.carlosmendez_weatherapp.api

import com.example.carlosmendez_weatherapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val APIKEY = "4a46bc43c4eb9dcbf74bbd28bf8b73e3"
interface WeatherService {
    @GET("data/2.5/forecast")
    suspend fun getWeatherByCity(@Query("q") city: String, @Query("appid") apikey: String = APIKEY) : Response<WeatherResponse>
}