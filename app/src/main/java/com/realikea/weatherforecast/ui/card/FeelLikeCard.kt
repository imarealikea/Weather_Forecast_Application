package com.realikea.weatherforecast.ui.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.realikea.weatherforecast.R
import com.realikea.weatherforecast.ui.WeatherState

@Composable
fun FeelLikeCard(state: WeatherState, modifier: Modifier, color: MaterialTheme){
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            modifier = Modifier
                .width(170.dp)
                .height(89.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 9.dp, topEnd = 9.dp, bottomStart = 9.dp, bottomEnd = 9.dp
                    )
                )
        ) {
            Column {
                Text(
                    text = stringResource(R.string.feel_like),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineSmall,
                    textDecoration = TextDecoration.None,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp)
                        .alpha(1f)
                )
                Text(
                    text = "${data.feelslikeCelsius.toInt()}°",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = TextDecoration.None,

                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
    }
}