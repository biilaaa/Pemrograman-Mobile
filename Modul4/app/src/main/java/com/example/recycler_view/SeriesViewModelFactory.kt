package com.example.recycler_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SeriesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SeriesViewModel::class.java)) {
            return SeriesViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}