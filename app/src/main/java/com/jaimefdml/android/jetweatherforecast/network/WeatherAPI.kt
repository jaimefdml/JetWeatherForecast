package com.jaimefdml.android.jetweatherforecast.network

import com.jaimefdml.android.jetweatherforecast.model.CurrentWeather
import com.jaimefdml.android.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {

    @GET(value = "data/2.5/weather")
    suspend fun getWeather(@Query("q") query: String,
                           @Query("appid") appID: String = Constants.API_KEY): CurrentWeather


}