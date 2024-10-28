@file:OptIn(ExperimentalMaterial3Api::class)

package com.realikea.weatherforecast.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeatherAppBar(viewModel: WeatherViewModel, modifier: Modifier){
    TopAppBar(
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Filled.Menu, null)
            }
        },
        actions = {
            IconButton(onClick = { viewModel.loadWeatherInfo() }) {
                Icon(Icons.Filled.Refresh, null)
            }
        },
    )
}