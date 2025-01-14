@file:OptIn(ExperimentalMaterial3Api::class)

package com.realikea.weatherforecast.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

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
import com.realikea.weatherforecast.ui.card.AqiCard
import com.realikea.weatherforecast.ui.card.AstroCard
import com.realikea.weatherforecast.ui.card.FeelLikeCard
import com.realikea.weatherforecast.ui.card.HeaderData
import com.realikea.weatherforecast.ui.card.HumidityCard
import com.realikea.weatherforecast.ui.card.StatusBox
import com.realikea.weatherforecast.ui.card.UVindexCard
import com.realikea.weatherforecast.ui.card.VisibilityCard
import com.realikea.weatherforecast.ui.card.WindCard
import com.realikea.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun DataScreen(
    state: WeatherState,
    modifier: Modifier,
    ){
    state.weatherInfo?.let{
        Box(){
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 1.dp, end = 16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderData(state = state, modifier = Modifier, color = MaterialTheme)
                //Spacer(Modifier.height(25.dp))
                Spacer(Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier) {
                        AqiCard(
                            modifier = Modifier,
                            color = MaterialTheme,
                            state = state,
                        )
                        Spacer(Modifier.height(20.dp))
                        UVindexCard(modifier = Modifier, color = MaterialTheme, state = state)
                        Spacer(Modifier.height(20.dp))
                        VisibilityCard(state = state, modifier = Modifier)
                        Spacer(Modifier.height(20.dp))
                        AstroCard(state = state)

                    }
                    Spacer(
                        Modifier
                            .width(2.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceAround) {
                        FeelLikeCard(modifier = Modifier, color = MaterialTheme, state = state)
                        Spacer(Modifier.height(20.dp))
                        HumidityCard(modifier = Modifier, color = MaterialTheme, state = state)
                        Spacer(Modifier.height(20.dp))
                        WindCard(state = state)
                    }
                }
                StatusBox(modifier = Modifier, vertical = Alignment.Bottom, state = state)
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(
){
    WeatherForecastTheme(){
        DataScreen(
            modifier = Modifier,
            state = WeatherState(
                WeatherInfo(
                    currentWeatherData = WeatherData(
                        lastUpdatedEpoch = 1730989800,
                        lastUpdated = "2024-01-23 20:30",
                        airQualityData = AirQualityData(
                            co = 1695.6,
                            no2 = 63.1,
                            o3 = 22.7,
                            so2 = 14.4,
                            pm2_5 = 47.0,
                            pm10 = 71.7
                        ),
                        code = 1003,
                        feelslikeCelsius = 28.8,
                        humidity = 70,
                        isDay = 0,
                        uv = 1.0,
                        uvIndex = UvIndexType.fromWeatherWeb(uv = 1.0),
                        weatherType = WeatherType.Overcast,
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
        )
    }
}