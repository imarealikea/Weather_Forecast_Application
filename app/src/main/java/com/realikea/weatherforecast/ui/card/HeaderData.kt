package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.realikea.weatherforecast.ui.WeatherState
import com.realikea.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun HeaderData(
    modifier: Modifier,
    state: WeatherState,
    color: MaterialTheme
){
        state.weatherInfo?.currentWeatherData?.let{ data ->
        Box(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = modifier.padding()
            ) {
                Text(
                    text = "${data.temperatureCelsius.toInt()}°",
                    style = MaterialTheme.typography.displayLarge,
                    color = color.colorScheme.onPrimaryContainer,
                    fontWeight = MaterialTheme.typography.displayLarge.fontWeight
                )
                Text(
                    text = data.weatherType.weatherDesc,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    softWrap = true,
                    modifier = Modifier.padding(end = 105.dp)

                )
                LocationName(state = state)

            }

            Image(
                painter = painterResource(data.weatherType.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(109.dp)
                    .height(109.dp)
                    .align(Alignment.TopEnd)
            )
        }
    }
}
@Composable
fun LocationName(state: WeatherState){
    state.weatherInfo?.currentLocationData?.let { data ->
        Column {
            Text(
                text = data.name,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = data.region,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = data.country,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeadPreview(
){
    WeatherForecastTheme {
        HeaderData(
            modifier = Modifier,
            state = WeatherState(
                WeatherInfo(
                    currentWeatherData = WeatherData(
                        lastUpdatedEpoch = 1730989800,
                        lastUpdated = "2024-01-23 20:30",
                        airQualityData = AirQualityData(co = 1695.6, no2 = 63.1, o3 = 22.7, so2 = 14.4, pm2_5 = 47.0, pm10 = 71.7),
                        code = 1003,
                        feelslikeCelsius = 28.8,
                        humidity = 70,
                        isDay = 0,
                        uv = 1.0,
                        uvIndex = UvIndexType.fromWeatherWeb(uv = 1.0),
                        weatherType = WeatherType.PatchySnowDay,
                        temperatureCelsius = 28.0,
                        usEpaIndex = 1,
                        usEpaIndexType = UsEpaIndex.fromWeatherWeb(usEpaIndex = 1),
                        windKph = 16.9,
                        windDirType = WindDirType.W,
                        visKM = 10.0
                    ),
                    currentLocationData = LocationData(
                        country = "Thailand",
                        localtime = "2024-01-23 20:34",
                        name = "Pak Kret",
                        region = "Nonthaburi"
                    ),
                    forecastDataList =
                        listOf(
                            ForecastDayData(
                                date = 1730678400,
                                day = DayData(
                                    maxtemp_c = 35.9,
                                    mintemp_c = 88.8
                                ),
                                astroDto = AstroData(
                                    sunrise = "06:13 AM",
                                    sunset = "05:50 PM",
                                    moonrise = "06:38 AM",
                                    moonset = "06:15 PM",
                                ),
                                hourForecast = listOf(
                                    HourForecastData(
                                        time_epoch = 1730912400,
                                        temp_c = 25.1,
                                        temp_f = 77.3,
                                        code = 58,
                                        humidity = 90
                                    ),
                                    HourForecastData(
                                        time_epoch = 1730916000,
                                        temp_c = 25.1,
                                        temp_f = 77.3,
                                        code = 58,
                                        humidity = 90
                                    ),
                                    HourForecastData(
                                        time_epoch = 1730937600,
                                        temp_c = 25.1,
                                        temp_f = 77.3,
                                        code = 58,
                                        humidity = 90
                                    )
                                )
                            )
                        )
                    )
            ),
            color = MaterialTheme
        )
    }
}