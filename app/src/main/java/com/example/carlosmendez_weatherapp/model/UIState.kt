package com.example.carlosmendez_weatherapp.model

sealed class UIState{
    object Loading: UIState()
    class Error(val error: Exception): UIState()
    class Success(val response: WeatherResponse): UIState()
}

enum class StateUI{
    LOADING,
    ERROR,
    SUCCESS
}
