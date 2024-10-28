package com.realikea.weatherforecast.network

import com.realikea.weatherforecast.domain.util.Resource
import com.realikea.weatherforecast.model.weather.GeocodeInfo
import com.realikea.weatherforecast.model.weather.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/current.json?key=(secret)&days=1&aqi=yes&alerts=no&")
    suspend fun getWeatherData(
        @Query("q") latLong: String
    ): WeatherDto
}
