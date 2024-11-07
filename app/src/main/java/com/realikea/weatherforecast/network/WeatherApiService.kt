package com.realikea.weatherforecast.network

import com.realikea.weatherforecast.domain.util.Resource
import com.realikea.weatherforecast.model.weather.GeocodeInfo
import com.realikea.weatherforecast.model.weather.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast.json?key=c50b95a942614682bc341859230106&days=2&aqi=yes&alerts=no&")
    suspend fun getWeatherData(
        @Query("q") latLong: String
    ): WeatherDto
}
