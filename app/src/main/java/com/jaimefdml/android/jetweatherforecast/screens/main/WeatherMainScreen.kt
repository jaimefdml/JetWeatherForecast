package com.jaimefdml.android.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jaimefdml.android.jetweatherforecast.data.DataOrException
import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather
import com.jaimefdml.android.jetweatherforecast.model.Weather
import com.jaimefdml.android.jetweatherforecast.widgets.WeatherAppBar

const val TAG = "WeatherMainScreen"
@Composable
fun WeatherMainScreen(navController: NavHostController,
                      mainViewModel: MainViewModel = hiltViewModel()) {
    val weatherData = produceState<DataOrException<CurrentWeather, Boolean, Exception>>(initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeather("Córdoba", "ES")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else {
        weatherData.data?.let {
            MainScaffold(currentWeather = it, navController = navController)
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(currentWeather: CurrentWeather, navController: NavHostController) {
    Scaffold(topBar = {
        WeatherAppBar(
            elevation = 5.dp,
            title = currentWeather.name,
            navController=navController,
            icon = Icons.Default.ArrowBack,
    ) {
            Log.d(TAG, "MainScaffold: Button clicked")
        }

    }) { paddingValues ->
        MainContent(
            modifier = Modifier.padding(paddingValues),
            currentWeather = currentWeather
        )

    }


}

@Composable
fun MainContent(modifier: Modifier = Modifier,
                currentWeather: CurrentWeather) {
    
    Text(text = currentWeather.name)


}
