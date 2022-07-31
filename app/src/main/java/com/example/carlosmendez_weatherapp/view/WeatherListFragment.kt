package com.example.carlosmendez_weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.carlosmendez_weatherapp.databinding.FragmentWeatherListBinding
import com.example.carlosmendez_weatherapp.model.UIState
import com.example.carlosmendez_weatherapp.model.WeatherResponse

private const val TAG = "ViewModel"

class WeatherListFragment: ViewModelFragment() {

    lateinit var binding: FragmentWeatherListBinding
    private val weatherAdapter by lazy {
        WeatherAdapter()
    }
    private val args: WeatherListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        viewModel.setLoading()
        configureObserver()
        return binding.root

    }

    private fun configureObserver() {
        Log.d(TAG, "configureObserver:START")
        viewModel.weatherListData.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                is UIState.Loading -> {
                    Log.d(TAG, "configureObserver:LOADING")
                    viewModel.getWeather(args.city)
//                    binding.rvWeatherList.adapter = weatherAdapter
                }
                is UIState.Error -> {
                    Log.d(TAG, "configureObserver:ERROR")
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoading.text = uiState.error.message
                }
                is UIState.Success -> {
                    Log.d(TAG, "configureObserver:SUCCESS")
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvLoading.visibility = View.GONE
                        weatherAdapter.setWeatherList(uiState.response.list)
                        rvWeatherList.adapter = weatherAdapter
                        tvCity.text = uiState.response.city.name.toString()
                        tvCountry.text = uiState.response.city.country.toString()
                    }
                }
            }
        }
    }

}