package com.example.carlosmendez_weatherapp.di

import com.example.carlosmendez_weatherapp.api.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {
    private val service = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHtttpClient())
        .build()
        .create(WeatherService::class.java)

    private fun provideHtttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(20,TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .build()
    }
}