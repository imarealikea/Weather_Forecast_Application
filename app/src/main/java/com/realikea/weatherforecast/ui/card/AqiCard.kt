package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.realikea.weatherforecast.R
import com.realikea.weatherforecast.model.weather.AirQualityData
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
        Card(
            modifier = Modifier
                .size(170.dp, 151.dp)
                .clickable {
                    showDialog = true
                }
                //.background(MaterialTheme.colorScheme.onPrimary),
        ) {
            Column(){
                Text(
                    text = stringResource(R.string.pm2_5),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineSmall,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .alpha(1f),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "${data.airQualityData.pm2_5}",
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.None,
                        style = MaterialTheme.typography.titleLarge,

                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "µg/m³",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(data.usEpaIndexType.indexDesc),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

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
                    text = "${weatherData.airQualityData.pm2_5} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.pm2_5.toFloat()/500 },
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
                    text = stringResource(R.string.pm10))
                Text(
                    text = "${weatherData.airQualityData.pm10} µg/m³"
                )
            }
            LinearProgressIndicator(
                progress = { weatherData.airQualityData.pm10.toFloat()/300 },
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
                    text = stringResource(R.string.co)
                )
                Text(
                    text = "${weatherData.airQualityData.co} µg/m³"
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
                    text = "${weatherData.airQualityData.no2} µg/m³"
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
                    text = "${weatherData.airQualityData.o3} µg/m³"
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
                    text = "${weatherData.airQualityData.so2} µg/m³"
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

@Preview
@Composable
fun CardPreview(
){
    WeatherForecastTheme {
        AqiCard(
            modifier = Modifier,
            state = WeatherState(
                WeatherInfo(
                    currentWeatherData = WeatherData(
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
                    )
                )
            ),
            color = MaterialTheme
        )
    }
}
