package com.jaimefdml.android.jetweatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.jaimefdml.android.jetweatherforecast.data.DataOrException
import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather
import com.jaimefdml.android.jetweatherforecast.model.Weather
import com.jaimefdml.android.jetweatherforecast.utils.formatDate
import com.jaimefdml.android.jetweatherforecast.widgets.WeatherAppBar
import java.util.Date

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
fun WeatherStateImage(imageUrl: String) {
    AsyncImage(model = imageUrl, contentDescription = "weather icon",
        modifier = Modifier.size(80.dp))
}

@Composable
fun MainContent(modifier: Modifier = Modifier,
                currentWeather: CurrentWeather) {

    val imageUrl = "https://openweathermap.org/img/wn/${currentWeather.weather.first().icon}@2x.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = formatDate(currentWeather.dt),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                //Image
                WeatherStateImage(imageUrl = imageUrl)
                Text(text="${currentWeather.main.temp} ºC", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                Text(text=currentWeather.weather.first().main, fontStyle = FontStyle.Italic)

            }

        }

    }

}
