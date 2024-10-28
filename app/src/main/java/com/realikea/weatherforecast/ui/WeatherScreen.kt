@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.realikea.weatherforecast.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.realikea.weatherforecast.model.weather.AirQualityData
import com.realikea.weatherforecast.model.weather.LocationData
import com.realikea.weatherforecast.model.weather.WeatherData
import com.realikea.weatherforecast.model.weather.WeatherInfo
import com.realikea.weatherforecast.model.weather.WeatherType
import com.realikea.weatherforecast.model.weather.subtype.UsEpaIndex
import com.realikea.weatherforecast.model.weather.subtype.UvIndexType
import com.realikea.weatherforecast.model.weather.subtype.WindDirType
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    state: WeatherState,
    modifier: Modifier,
    viewModel: WeatherViewModel
) { state.weatherInfo?.let{
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        //containerColor = MaterialTheme.colorScheme.primaryContainer,
                        //titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
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
                    modifier = Modifier
                )
            },

        ){ padding ->
            DataScreen(state = state,modifier = modifier.padding(padding))
        }

    }
}

@Preview
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open)
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Drawer")
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Content Page")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPreview(){
    DataScreen(
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
    )
}