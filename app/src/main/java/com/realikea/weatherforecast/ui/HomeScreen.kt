package com.realikea.weatherforecast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
import com.realikea.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun HomeScreen(state: WeatherState,navController: NavHostController = rememberNavController()){
    Box(Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        ){
        state.weatherInfo?.let { data ->
            Column() {
                Row(Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        stringResource(R.string.app_name),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            //.padding(start = 16.dp)
                                ,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            Icons.Outlined.Settings,
                            null,
                            modifier = Modifier
                                .size(32.dp),
                            tint = MaterialTheme.colorScheme.onBackground)
                    }
                }
                //Spacer(Modifier.height(8.dp))
                Row(Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)) {
                    Image(painterResource(R.drawable.livelocation), null, Modifier.padding(end = 8.dp))
                    Text(
                        stringResource(R.string.current_location),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineMedium
                    ) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(84.dp)
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                        .clickable(onClick = { navigateToWeather(navController) }),
                ) {
                    Column(Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)){
                        Text(
                            text = data.currentLocationData.name,
                            style = MaterialTheme.typography.displaySmall,
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = data.currentWeatherData.weatherType.weatherDesc,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            softWrap = true
                        )
                    }
                    Row(modifier = Modifier.align(Alignment.CenterEnd)){
                        Text(
                            text = "${data.currentWeatherData.temperatureCelsius.toInt()}°",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Image(
                            painter = painterResource(data.currentWeatherData.weatherType.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(40.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                Row(Modifier
                    .align(Alignment.Start)
                    .padding(16.dp)) {
                    //Image(painterResource(R.drawable.livelocation), null, Modifier.padding(end = 8.dp))
                    Text(
                        stringResource(R.string.saved_locations),
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineMedium
                    ) }
            }
        }
    }
}

/*fun LazyLocationWeather(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = { navigateToWeather(navController) }),
    ) {
        Column(Modifier
            .padding(start = 16.dp)
            .align(Alignment.CenterStart)){
            Text(
                text = data.currentLocationData.name,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = data.currentWeatherData.weatherType.weatherDesc,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                softWrap = true
            )
        }
        Row(modifier = Modifier.align(Alignment.CenterEnd)){
            Text(
                text = "${data.currentWeatherData.temperatureCelsius.toInt()}°",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Image(
                painter = painterResource(data.currentWeatherData.weatherType.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    WeatherForecastTheme(){
        HomeScreen(
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
        )
    )
    }
}