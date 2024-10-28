package com.realikea.weatherforecast.model.weather

data class GeocodeInfo(
    val name: String,
    val country: String,
    val local_names: Map<String, String>,
)