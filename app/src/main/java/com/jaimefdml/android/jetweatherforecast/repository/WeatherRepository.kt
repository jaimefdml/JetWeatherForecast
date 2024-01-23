package com.jaimefdml.android.jetweatherforecast.repository

import android.util.Log
import com.jaimefdml.android.jetweatherforecast.data.DataOrException
import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather
import com.jaimefdml.android.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepository @Inject constructor(private val api: WeatherAPI) {

    companion object Constants {
        const val TAG: String = "WeatherRepository"
    }
    suspend fun getWeather(city: String, countryCode: String = "ES"):
            DataOrException<CurrentWeather, Boolean, Exception> {
        val response = try {
            Log.d(TAG, "getWeather: city and country = $city,$countryCode")
            api.getWeather(query = "$city,$countryCode")
        } catch (exception: Exception) {
            Log.e(TAG, "Exception: ${exception.message}")
            return DataOrException(e = exception)
        }
        Log.d(TAG, "getWeather: $response")
        return DataOrException(data = response)
    }
}