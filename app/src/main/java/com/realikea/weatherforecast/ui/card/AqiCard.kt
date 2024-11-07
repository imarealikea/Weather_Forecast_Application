package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
fun AqiCard(
    modifier: Modifier,
    color: MaterialTheme,
    state: WeatherState,
){
    state.weatherInfo?.currentWeatherData?.let { data ->
        var showDialog by remember { mutableStateOf(false) }
        val pm2_5Color = colorResource(
            if (data.airQualityData.pm2_5 <= 15) {
                R.color.green_aqi
            } else if (data.airQualityData.pm2_5 <= 40) {
                R.color.yellow_aqi
            } else if (data.airQualityData.pm2_5 <= 65) {
                R.color.orange_aqi
            } else if (data.airQualityData.pm2_5 <= 150) {
                R.color.red_aqi
            } else if (data.airQualityData.pm2_5 <= 250) {
                R.color.purple_aqi
            } else if (data.airQualityData.pm2_5 <= 500) {
                R.color.brown_aqi
            } else {
                R.color.brown_aqi
            }
        )
        Card(
            modifier = Modifier
                .size(170.dp, 151.dp)
                .clickable {
                    showDialog = true
                }
                //.background(MaterialTheme.colorScheme.onPrimary),
        ) {
                Text(
                    text = stringResource(R.string.pm2_5),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineSmall,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .alpha(1f),
                )
                Box(
                    contentAlignment = Alignment.Center,
                    //verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    ArcPM2_5(state, pm2_5Color, Modifier.fillMaxWidth() )
                    Column(Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally){
                        Text(
                            text = "${data.airQualityData.pm2_5.toInt()}",
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.None,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = stringResource(R.string.microgram_per_m3),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = stringResource(data.usEpaIndexType.indexDesc),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            softWrap = true
                        )
                    }
                }
                if (showDialog) {
                        Dialog(onDismissRequest = { showDialog = false }) {
                            Surface(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight(),
                                shape = MaterialTheme.shapes.large,
                            ){
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()

                                ){
                                    Row(verticalAlignment = Alignment.CenterVertically)
                                    {
                                        Text(
                                            text = stringResource(R.string.air_quality),
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        IconButton(
                                            onClick = { showDialog = false },
                                        ) {
                                            Icon(
                                                Icons.Filled.Close,
                                                contentDescription = stringResource(R.string.close)
                                            )
                                        }
                                    }
                                    AirQualityDialog(
                                        weatherData = data,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                    }
                }
            }
        }
}

@Composable
fun AirQualityDialog(
    weatherData: WeatherData,
    modifier: Modifier
){

    Column{
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = stringResource(R.string.pm2_5))
                Text(
                    text = "${weatherData.airQualityData.pm2_5.toInt()} µg/m³"
                )
            }

            LinearProgressIndicator(
                progress = { weatherData.airQualityData.pm2_5.toFloat()/250 },
                modifier = Modifier
                    .fillMaxWidth(),
                strokeCap = StrokeCap.Round,
                color = colorResource(if (weatherData.airQualityData.pm2_5 <= 15){ R.color.green_aqi }
                else if (weatherData.airQualityData.pm2_5 <= 40) {R.color.yellow_aqi}
                else if (weatherData.airQualityData.pm2_5 <= 65) { R.color.orange_aqi }
                else if (weatherData.airQualityData.pm2_5 <= 150) { R.color.red_aqi }
                else if (weatherData.airQualityData.pm2_5 <= 250) { R.color.purple_aqi }
                else if (weatherData.airQualityData.pm2_5 <= 500) { R.color.brown_aqi }
                else { R.color.black })
                )
        }
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = stringResource(R.string.pm10))
                Text(
                    text = "${weatherData.airQualityData.pm10.toInt()} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.pm10.toFloat()/300 },
                modifier = Modifier
                    .fillMaxWidth()
                ,

            )
        }
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = stringResource(R.string.co)
                )
                Text(
                    text = "${weatherData.airQualityData.co.toInt()} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.co.toFloat()/60 },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = stringResource(R.string.no2)
                )
                Text(
                    text = "${weatherData.airQualityData.no2.toInt()} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.no2.toFloat()/200 },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = "O3")
                Text(
                    text = "${weatherData.airQualityData.o3.toInt()} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.o3.toFloat()/100 },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
        Column{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,){
                Text(
                    text = "SO2")
                Text(
                    text = "${weatherData.airQualityData.so2.toInt()} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.so2.toFloat()/350 },
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }

}

@Composable
fun ArcPM2_5(state: WeatherState, pm2_5Color: Color,modifier: Modifier) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        val sweepAngle = data.airQualityData.pm2_5.toFloat() / 500f * 360f
        Canvas(modifier = Modifier.size(125.dp).padding(top = 6.dp)) {
            val center = size/2f
            drawArc(
                color = pm2_5Color,
                startAngle = 180f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 35f)
            )
            drawArc(
                color = Color.LightGray,
                startAngle = sweepAngle - 180f,
                sweepAngle = 180f - sweepAngle,
                useCenter = false,
                style = Stroke(width = 35f)
            )
        }
    }
}

@Preview
@Composable
fun ArcPreview(){
    WeatherForecastTheme {
        ArcPM2_5(
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
            pm2_5Color = colorResource(R.color.yellow_aqi)
        )
    }
}

@Preview(locale = "th")
@Composable
fun CardPreview(
){
    WeatherForecastTheme {
        AqiCard(
            modifier = Modifier,
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
            ),
            color = MaterialTheme
        )
    }
}

@Preview (showBackground = true)
@Composable
private fun DialogPreview(){
    WeatherForecastTheme {
        AirQualityDialog(
            weatherData = WeatherData(
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
            modifier = Modifier
        )
    }
}