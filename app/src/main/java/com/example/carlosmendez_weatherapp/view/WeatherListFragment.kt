package com.example.carlosmendez_weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.carlosmendez_weatherapp.databinding.FragmentWeatherListBinding
import com.example.carlosmendez_weatherapp.model.UIState
import com.example.carlosmendez_weatherapp.model.WeatherResponse

class WeatherListFragment: ViewModelFragment() {
    lateinit var binding: FragmentWeatherListBinding
    private val weatherAdapter by lazy {
        WeatherAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.weatherListData.observe(viewLifecycleOwner) {
            uiState ->
            when(uiState) {
                is UIState.Loading -> {
                    viewModel.getWeather("Atlanta")
                    binding.rvWeatherList.adapter = weatherAdapter
                }
                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoading.text = uiState.error.message
                }
                is UIState.Success -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoading.visibility = View.GONE
                        weatherAdapter.setWeatherList(uiState.response.list)
                    }
                }
            }
        }
    }

}