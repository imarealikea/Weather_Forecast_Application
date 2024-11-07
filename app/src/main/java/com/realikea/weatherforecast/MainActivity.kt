@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.realikea.weatherforecast

//import com.realikea.weatherforecast.ui.DataScreen
import android.Manifest
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.realikea.weatherforecast.ui.ErrorWeather
import com.realikea.weatherforecast.ui.MainScreen
import com.realikea.weatherforecast.ui.WeatherApp
import com.realikea.weatherforecast.ui.WeatherViewModel
import com.realikea.weatherforecast.ui.theme.WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by this.viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge(SystemBarStyle.auto(Color. TRANSPARENT, Color. TRANSPARENT))
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.run {
                loadWeatherInfo()
            }
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            WeatherForecastTheme {
                val navController = rememberNavController()
                MainScreen(state = viewModel.state, modifier = Modifier.safeDrawingPadding(), viewModel = viewModel)
                    if (viewModel.state.isLoading) {
                        Box(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    viewModel.state.error?.let { error ->
                        ErrorWeather(error, viewModel)
                }
            }
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForecastTheme {
        val viewModel: WeatherViewModel by this.viewModels()
        DataScreen(state = viewModel.state)
    }
}
*/