package com.realikea.weatherforecast.network

import com.realikea.weatherforecast.model.weather.subtype.AirQualityDto
import com.squareup.moshi.Json

data class WeatherDataDto(
    @field:Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @field:Json(name = "last_updated")
    val lastUpdated: String,
    @field:Json(name = "temp_c")
    val tempC: Double,
    @field:Json(name = "is_day")
    val isDay: Int,
    @field:Json(name = "feelslike_c")
    val feelslikeCelsius: Double,
    @field:Json(name = "humidity")
    val humidity: Int,
    @field:Json(name = "uv")
    val uv: Double,
    @field:Json(name = "air_quality")
    val airQuality: AirQualityDto,
    @field:Json(name = "condition")
    val condition: ConditionDataDto,
    @field:Json(name = "wind_kph")
    val windKph: Double,
    @field:Json(name = "wind_dir")
    val windDir: String,
    @field:Json(name = "vis_km")
    val visKM: Double
)