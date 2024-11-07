package com.realikea.weatherforecast.ui.card

import android.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.realikea.weatherforecast.R
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
fun AstroCard(state: WeatherState){
    state.weatherInfo?.forecastDataList?.let { data ->
        var showDialog by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .size(170.dp, 151.dp)
                .clickable {
                    showDialog = true
                }
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 10.dp)
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(R.string.sunrise),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier

                    )
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.sunrise),
                        modifier = Modifier,
                        contentDescription = null
                    )
                    Text(
                        text = data[0].astroDto.sunrise,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    Text(
                        text = stringResource(R.string.sunset),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .alpha(1f),
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.sunset),
                        modifier = Modifier,
                        contentDescription = null
                    )
                    Text(
                        text = data[0].astroDto.sunset,
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AstroPreview(){
    WeatherForecastTheme {
        AstroCard(
            state = WeatherState(
                WeatherInfo(
                    currentWeatherData = WeatherData(
                        lastUpdatedEpoch = 1730989800,
                        lastUpdated = "2024-01-23 20:30",
                        airQualityData = AirQualityData(co = 1695.6, no2 = 63.1, o3 = 22.7, so2 = 14.4, pm2_5 = 125.0, pm10 = 71.7),
                        code = 1003,
                        feelslikeCelsius = 28.8,
                        humidity = 70,
                        isDay = 0,
                        uv = 1.0,
                        uvIndex = UvIndexType.fromWeatherWeb(uv = 1.0),
                        weatherType = WeatherType.Overcast,
                        temperatureCelsius = 28.0,
                        usEpaIndex = 1,
                        usEpaIndexType = UsEpaIndex.fromWeatherWeb(usEpaIndex = 3),
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
                    forecastDataList = listOf(
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
            )
        )
    }
}