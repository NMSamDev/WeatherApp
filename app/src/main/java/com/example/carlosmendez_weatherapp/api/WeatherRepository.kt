package com.example.carlosmendez_weatherapp.api

import android.util.Log
import com.example.carlosmendez_weatherapp.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WeatherRepository {
    suspend fun getWeatherByCity(city: String): Flow<UIState>
}
const val TAG = "networkCall"
class WeatherRepositoryImpl(private val service: WeatherService): WeatherRepository{
    override suspend fun getWeatherByCity(city: String): Flow<UIState> =
        flow {
            try {
                val response = service.getWeatherByCity(city)
                if (response.isSuccessful) {
                    Log.d(TAG, "getWeatherByCity:1-------------------------")
                    emit(response.body()?.let { weatherResponse ->
                        UIState.Success(weatherResponse)
                    } ?: throw Exception("Empty response"))
                }
                else {
                    Log.d(TAG, "getWeatherByCity:2--------------------------")
                    throw Exception("Failed network call")
                }
            }
            catch (e: Exception){
                emit(UIState.Error(e))
            }
        }

}