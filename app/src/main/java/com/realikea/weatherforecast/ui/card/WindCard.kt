package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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

@Composable
fun WindCard(
    state: WeatherState,
){
    state.weatherInfo?.currentWeatherData?.let{ data ->
        var showDialog by remember { mutableStateOf(false) }
        Card(
            modifier = Modifier
                .width(170.dp)
                .height(151.dp)
                .alpha(1f)
                .clickable {
                    showDialog = false
                },
        ){ Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.Start),


        ){
            Text(
                text = stringResource(id = R.string.wind),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,

                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .alpha(1f),
            )
            Spacer(modifier = Modifier.height(3.dp))
            Column{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.wind),
                        contentDescription = "Wind Icon",
                        modifier = Modifier
                            .height(60.dp)
                            .width(73.dp)
                            .padding(start = 10.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Column(
                        modifier = Modifier
                    )
                    {
                        Text(
                            text = "${data.windKph}",
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Spacer(modifier = Modifier.height((-5).dp))
                        Text(
                            text = stringResource(R.string.km_hr),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
                Text(
                    text = stringResource(data.windDirType.windDirection),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 2.dp),
                    textAlign = TextAlign.Center
                )
            }
            }

        }
        if (showDialog){
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                    )
                    {
                        Row()
                        {
                            Text(
                                text = stringResource(R.string.wind),
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
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WindCardPreview(
){
}
