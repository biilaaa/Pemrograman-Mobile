package com.example.recycler_view

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SeriesViewModel : ViewModel() {

    private val _seriesList = MutableStateFlow<List<Series>>(emptyList())
    val seriesList: StateFlow<List<Series>> get() = _seriesList

    private val _selectedSeries = MutableStateFlow<Series?>(null)
    val selectedSeries: StateFlow<Series?> get() = _selectedSeries

    fun setSeriesList(list: List<Series>) {
        _seriesList.value = list
        Log.d("SeriesViewModel", "Data item masuk ke list: $list")
    }

    fun selectSeries(series: Series) {
        _selectedSeries.value = series
        Log.d("SeriesViewModel", "Item dipilih: ${series.title}")
    }
}