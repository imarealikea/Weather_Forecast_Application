package com.realikea.weatherforecast.network.forecast

data class DayDto (
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val daily_chance_of_rain: Int
)