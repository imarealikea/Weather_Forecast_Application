package com.realikea.weatherforecast.model.weather

data class WeatherInfo(
    //val weatherDataPerDay: WeatherData,
    val currentWeatherData: WeatherData,
    val currentLocationData: LocationData,
    val forecastDataList: List<ForecastDayData>
    //val airQualityData: AirQualityData
)