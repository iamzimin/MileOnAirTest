package com.evg.mileonairtest

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evg.purchases.presentation.PurchasesRoot
import com.evg.registration.presentation.RegistrationRoot
import com.evg.resource.custom.BackButton
import com.evg.resource.theme.AppTheme
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.settings.presentation.SettingsRoot

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backgroundColor = AppTheme.colors.background

    val backgroundGradient = remember(backgroundColor) {
        Brush.linearGradient(
            colors = listOf(
                backgroundColor.let { bg ->
                    Color(
                        red = (bg.red + 0.05f).coerceAtMost(1f),
                        green = (bg.green + 0.05f).coerceAtMost(1f),
                        blue = (bg.blue + 0.05f).coerceAtMost(1f),
                        alpha = bg.alpha
                    )
                },
                backgroundColor
            ),
            start = Offset(0f, 0f),
            end = Offset(1000f, 1000f)
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .background(backgroundGradient),
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    BackButton(
                        onClick = {
                            if (navController.previousBackStackEntry != null) {
                                navController.popBackStack()
                            }
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = Color.Transparent,
                    titleContentColor = AppTheme.colors.text,
                )
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.Settings,
        ) {
            composable<Route.Settings> {
                SettingsRoot(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    onRegistrationScreen = {
                        navController.navigate(route = Route.Registration)
                    },
                    onPurchasesScreen = {
                        navController.navigate(route = Route.Purchases)
                    }
                )
            }
            composable<Route.Registration> {
                RegistrationRoot(
                    modifier = Modifier.fillMaxSize().padding(paddingValues),
                    onPreviousScreen = {
                        navController.popBackStack()
                    },
                )
            }
            composable<Route.Purchases> {
                PurchasesRoot(
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