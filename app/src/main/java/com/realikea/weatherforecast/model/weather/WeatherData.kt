package com.realikea.weatherforecast.model.weather

import com.realikea.weatherforecast.model.weather.subtype.UsEpaIndex
import com.realikea.weatherforecast.model.weather.subtype.UvIndexType
import com.realikea.weatherforecast.model.weather.subtype.WeatherConData
import com.realikea.weatherforecast.model.weather.subtype.WindDirType

data class WeatherData(
    val temperatureCelsius: Double,
    val lastUpdated: String,
    val isDay: Int,
    val code: Int,
    val weatherType: WeatherType,
    val feelslikeCelsius: Double,
    val humidity: Int,
    val uv: Double,
    val uvIndex: UvIndexType,
    val usEpaIndex: Int,
    val usEpaIndexType: UsEpaIndex,
    val airQualityData: AirQualityData,
    val windKph: Double,
    val windDirType: WindDirType,
    val visKM: Double
)