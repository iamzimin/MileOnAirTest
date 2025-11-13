package com.evg.mileonairtest

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.settings.presentation.SettingsRoot

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val startDestination = Route.Settings

    Scaffold(
        modifier = Modifier.fillMaxSize().imePadding(),
        containerColor = AppTheme.colors.background,
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.background(AppTheme.colors.background),
        ) {
            composable<Route.Settings> {
                SettingsRoot(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MainScreenPreview() {
    MileOnAirTestTheme {
        MainScreen()
    }
}