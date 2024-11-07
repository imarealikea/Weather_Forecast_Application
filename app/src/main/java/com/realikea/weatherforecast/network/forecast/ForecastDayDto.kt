package com.realikea.weatherforecast.network.forecast

import com.squareup.moshi.Json

data class ForecastDayDto(
    @field:Json(name = "date_epoch")
    val date_epoch: Int,
    @field:Json(name = "day")
    val day: DayDto,
    @field:Json(name = "astro")
    val astro: AstroDto,
    @field:Json(name = "hour")
    val hourForecast: List<HourForecastDto>
)