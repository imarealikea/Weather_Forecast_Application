@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.realikea.weatherforecast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

sealed class AppScreen(val route: String) {
    object Home : AppScreen("home")
    object Weather : AppScreen("weather")
    object Settings : AppScreen("settings")
}

fun navigateToHome(navController: NavHostController) {
    navController.navigate(AppScreen.Home.route)
}

fun navigateToWeather(navController: NavHostController) {
    navController.navigate(AppScreen.Weather.route)
}

fun navigateToSettings(navController: NavHostController) {
    navController.navigate(AppScreen.Settings.route)
}

@Composable
fun MainScreen(
    state: WeatherState,
    modifier: Modifier,
    viewModel: WeatherViewModel,
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.Weather.route
    ){
        composable(AppScreen.Home.route){ HomeScreen(state, navController) }
        composable(AppScreen.Weather.route){ WeatherApp(
            state = state,
            modifier = modifier,
            viewModel = viewModel,
            navController = navController
        )}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    state: WeatherState,
    modifier: Modifier,
    viewModel: WeatherViewModel,
    navController: NavHostController
) { state.weatherInfo?.let{

        Scaffold(
            topBar = {
                WeatherAppBar(viewModel, modifier, navController)
            }
        ){ innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)){
                DataScreen(
                    state = state,
                    modifier = modifier
                        .background(color = MaterialTheme.colorScheme.surfaceContainer)

                )
            }
        }

    }
}

@Composable
fun ErrorWeather(error: String,viewModel: WeatherViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(topBar ={ TopAppBar(
            title = {
                Text(
                    text = ""
                )
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
        })
        { padding ->
            Column(modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround){
                Surface(Modifier.clip(CircleShape).align(Alignment.CenterHorizontally)){
                    Image(
                        painter = painterResource(R.drawable.exclamination),
                        contentDescription = "",
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.errorContainer)
                            .size(100.dp)
                            .padding(20.dp),
                        alignment = Alignment.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = when (error) {
                                "HTTPS 502 " -> "HTTPS 502 Bad Gateway"
                                "HTTP 502 " -> "HTTP 502 Bad Gateway"
                                "Unable to resolve host \"api.weatherapi.com\": No address associated with hostname" -> "Unable to connect to server"
                                else -> error
                            },
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
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
    )
}