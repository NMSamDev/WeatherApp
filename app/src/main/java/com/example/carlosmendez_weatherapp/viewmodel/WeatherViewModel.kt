package com.example.carlosmendez_weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlosmendez_weatherapp.api.WeatherRepository
import com.example.carlosmendez_weatherapp.model.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

const val TAG = "WeatherViewModel"

class WeatherViewModel(
    private val repository: WeatherRepository,
    private val dispatcher: CoroutineDispatcher
    ): ViewModel() {

    private val _weatherListData = MutableLiveData<UIState>()
    val weatherListData: LiveData<UIState> get() = _weatherListData

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler {
            coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext \nMessage: ${throwable.localizedMessage}", throwable)
        }
    }

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    fun getWeather(city: String) {
        viewModelSafeScope.launch(dispatcher) {
            repository.getWeatherByCity(city).collect{
                state ->
                _weatherListData.postValue(state)
            }
        }
    }

}