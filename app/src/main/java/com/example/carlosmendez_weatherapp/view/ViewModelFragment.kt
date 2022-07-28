package com.example.carlosmendez_weatherapp.view

import androidx.fragment.app.Fragment
import com.example.carlosmendez_weatherapp.di.DI

open class ViewModelFragment: Fragment() {
    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}