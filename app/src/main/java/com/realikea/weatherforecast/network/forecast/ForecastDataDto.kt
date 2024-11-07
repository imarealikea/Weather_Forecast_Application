package com.realikea.weatherforecast.network.forecast

import com.squareup.moshi.Json

data class ForecastDataDto(
    @field:Json(name = "forecastday")
    val forecastday: List<ForecastDayDto>
)
