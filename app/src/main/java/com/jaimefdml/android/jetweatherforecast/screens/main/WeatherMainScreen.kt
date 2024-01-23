package com.jaimefdml.android.jetweatherforecast.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jaimefdml.android.jetweatherforecast.data.DataOrException
import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather

@Composable
fun WeatherMainScreen(navController: NavHostController,
                      mainViewModel: MainViewModel = hiltViewModel()) {

    ShowData(mainViewModel)

}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<CurrentWeather, Boolean, Exception>>(initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeather("Córdoba", "ES")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        weatherData.data?.let {
            Text(text = "Weather for city is ${weatherData.data}")
        }
    }
}