package com.realikea.weatherforecast.domain.repository

import com.realikea.weatherforecast.model.weather.WeatherInfo
import com.realikea.weatherforecast.domain.util.Resource
import com.realikea.weatherforecast.model.weather.GeocodeInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}
