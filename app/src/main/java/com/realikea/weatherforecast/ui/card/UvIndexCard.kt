package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun UVindexCard(
    state: WeatherState,
    modifier: Modifier,
    color: MaterialTheme,
){
    state.weatherInfo?.currentWeatherData?.let {data ->
        var showDialog by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .width(170.dp)
                .height(151.dp)
                .clickable {
                    showDialog = true
                }
        ) {
                Column {
                    Text(
                        text = stringResource(R.string.uv_index_title),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp, bottom = 6.dp)

                            .height(18.dp)
                            .alpha(1f)


                    )
                    Box(modifier = Modifier.align(Alignment.CenterHorizontally)){
                        Image(
                            painter = painterResource(data.uvIndex.colorIndex),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .fillMaxSize()
                                .padding(bottom = 25.dp, start = 10.dp, end = 10.dp)
                            ,
                        )

                        Column(modifier = Modifier.padding(top = 20.dp)){
                            Text(
                                text = "${data.uv.toInt()}",
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(182.dp)
                                    .height(49.dp),
                            )
                            Text(
                                text = stringResource(data.uvIndex.uvIndexDesc),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(182.dp)
                            )
                        }
                }
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
                            text = stringResource(R.string.uv_index_title),
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
                        UvIndexDialog(
                            weatherData = data,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                    }
                }
            }
        }
    }
}

@Composable
fun UvIndexDialog(
    weatherData: WeatherData,
    modifier: Modifier,
){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(
            text = "${weatherData.uv}",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(weatherData.uvIndex.uvIndexDesc),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(weatherData.uvIndex.recommendDesc),
            style = MaterialTheme.typography.bodyLarge

        )
    }
}
@Preview
@Composable
fun UvPreview(
){
}