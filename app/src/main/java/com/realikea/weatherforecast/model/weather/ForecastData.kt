package com.realikea.weatherforecast.model.weather

data class ForecastDayData(
    val date: Int,
    val day: DayData,
    val astroDto: AstroData,
    val hourForecast: List<HourForecastData>
)

data class AstroData(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
)

data class DayData(
    val maxtemp_c: Double,
    val mintemp_c: Double,
)

data class HourForecastData(
    val time_epoch: Int,
    val temp_c: Double,
    val temp_f: Double,
    val code: Int,
    val humidity: Int,
)