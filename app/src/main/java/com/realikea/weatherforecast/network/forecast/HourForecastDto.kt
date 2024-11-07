package com.realikea.weatherforecast.network.forecast

import com.realikea.weatherforecast.network.ConditionDataDto
import com.squareup.moshi.Json

data class HourForecastDto(
    @field:Json(name = "time_epoch")
    val time_epoch: Int,
    @field:Json(name = "temp_c")
    val temp_c: Double,
    @field:Json(name = "temp_f")
    val temp_f: Double,
    @field:Json(name = "condition")
    val condition: ConditionDataDto,
    @field:Json(name = "humidity")
    val humidity: Int,
)