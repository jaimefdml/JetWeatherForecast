package com.jaimefdml.android.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import com.jaimefdml.android.jetweatherforecast.data.DataOrException
import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather
import com.jaimefdml.android.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeather(cityName: String, countryCode: String): DataOrException<CurrentWeather, Boolean, Exception> {
        return repository.getWeather(cityName, countryCode)
    }
}