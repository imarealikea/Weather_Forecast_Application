package com.realikea.weatherforecast.network

import com.squareup.moshi.Json

data class CurrentDataDto(
    val dt: Long,
    val temp: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @field:Json(name = "dew_point")
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @field:Json(name = "wind_speed")
    val windSpeed: Double,
    @field:Json(name = "wind_deg")
    val windDeg: Int,
    @field:Json(name = "wind_gust")
    val windGust: Double,
    //@field:Json(name = "weather")
    //val weather: WeatherConDataDto,
)