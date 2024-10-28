package com.realikea.weatherforecast.data.respository

import com.realikea.weatherforecast.data.mappers.toWeatherInfo
import com.realikea.weatherforecast.domain.repository.WeatherRepository
import com.realikea.weatherforecast.model.weather.WeatherInfo
import com.realikea.weatherforecast.network.WeatherApiService
import com.realikea.weatherforecast.domain.util.Resource
import com.realikea.weatherforecast.model.weather.GeocodeInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    latLong = "${lat},${long}"
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}