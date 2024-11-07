package com.realikea.weatherforecast.data.mappers


import android.annotation.SuppressLint
import com.realikea.weatherforecast.model.weather.AirQualityData
import com.realikea.weatherforecast.model.weather.AstroData
import com.realikea.weatherforecast.model.weather.DayData

import com.realikea.weatherforecast.model.weather.ForecastDayData
import com.realikea.weatherforecast.model.weather.HourForecastData
import com.realikea.weatherforecast.model.weather.LocationData
import com.realikea.weatherforecast.model.weather.WeatherData
import com.realikea.weatherforecast.model.weather.WeatherInfo
import com.realikea.weatherforecast.model.weather.WeatherType
import com.realikea.weatherforecast.model.weather.subtype.UsEpaIndex
import com.realikea.weatherforecast.model.weather.subtype.UvIndexType
import com.realikea.weatherforecast.model.weather.subtype.WindDirType
import com.realikea.weatherforecast.network.LocationDataDto
import com.realikea.weatherforecast.network.WeatherDataDto
import com.realikea.weatherforecast.network.WeatherDto
import com.realikea.weatherforecast.network.forecast.ForecastDataDto
import com.realikea.weatherforecast.network.forecast.ForecastDayDto
import com.realikea.weatherforecast.network.forecast.HourForecastDto

@SuppressLint("NewApi")
fun WeatherDataDto.toWeatherDataMap(): WeatherData {
    return (
            WeatherData(
                lastUpdatedEpoch = lastUpdatedEpoch,
                temperatureCelsius = tempC,
                lastUpdated = lastUpdated,
                code = condition.code,
                isDay = isDay,
                uv = uv,
                weatherType = WeatherType.fromWeatherWeb(this.condition.code,this.isDay),
                feelslikeCelsius = feelslikeCelsius,
                humidity = humidity,
                usEpaIndex = airQuality.usEpaIndex,
                uvIndex = UvIndexType.fromWeatherWeb(this.uv),
                usEpaIndexType = UsEpaIndex.fromWeatherWeb(this.airQuality.usEpaIndex),
                airQualityData = AirQualityData(airQuality.co,airQuality.no2,airQuality.o3,airQuality.pm10,airQuality.pm2_5,airQuality.so2),
                windKph = windKph,
                windDirType = WindDirType.fromWeatherWeb(windDir),
                visKM = visKM
            )
            )
}

@SuppressLint("NewApi")
fun LocationDataDto.toLocationDataMap(): LocationData {
    return LocationData(
        name = locationName,
        region = regionName,
        country = countryName,
        localtime = localtime
    )
}

@SuppressLint("NewApi")
fun ForecastDataDto.toForecastDataMap(): List<ForecastDayData> {
    return forecastday.map { it.toForecastData() }
}

@SuppressLint("NewApi")
fun ForecastDayDto.toForecastData(): ForecastDayData {
    return ForecastDayData(
            date = date_epoch,
            astroDto = AstroData(
                sunrise = astro.sunrise,
                sunset = astro.sunset,
                moonrise = astro.moonrise,
                moonset = astro.moonset
            ),
            day = DayData(
                maxtemp_c = day.maxtemp_c,
                mintemp_c = day.mintemp_c,
            ),
            hourForecast = hourForecast.map {
                HourForecastData(
                    time_epoch = it.time_epoch,
                    temp_c = it.temp_c,
                    temp_f = it.temp_f,
                    code = it.condition.code,
                    humidity = it.humidity,
                )
            }
        )
}

@SuppressLint("NewApi")
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val locationDataMap = locationData.toLocationDataMap()
    val currentWeatherData = weatherDataMap
    val forecastData = forecastData.toForecastDataMap()
    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        currentLocationData = locationDataMap,
        forecastDataList = forecastData
    )
}
