package com.evg.mileonairtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MileOnAirTestTheme {
                window?.let { window ->
                    window.navigationBarColor = AppTheme.colors.windowControllerBackground.toArgb()
                    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
                    windowInsetsController.isAppearanceLightNavigationBars = false
                }

                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MileOnAirTestTheme {
        MainScreen()
    }
}