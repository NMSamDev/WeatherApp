package com.example.carlosmendez_weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_weatherapp.databinding.FragmentStartingPageBinding

class StartingPageFragment: ViewModelFragment() {

    lateinit var binding: FragmentStartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.btnSearch.setOnClickListener{
            findNavController().navigate(StartingPageFragmentDirections.actionStartingPageToWeatherList(binding.etCity.text.toString()))
        }

        return binding.root
    }

}