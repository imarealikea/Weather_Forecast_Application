package com.realikea.weatherforecast.network
import com.realikea.weatherforecast.network.forecast.ForecastDataDto
import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "location")
    val locationData: LocationDataDto,
    @field:Json(name = "current")
    val weatherData: WeatherDataDto,
    @field:Json(name = "forecast")
    val forecastData: ForecastDataDto

)