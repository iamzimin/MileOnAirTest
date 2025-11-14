package com.evg.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evg.settings.presentation.mvi.SettingsViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SettingsRoot(
    modifier: Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
    onRegistrationScreen: () -> Unit,
    onPurchasesScreen: () -> Unit,
) {
    SettingsScreen(
        modifier = modifier,
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
        onRegistrationScreen = onRegistrationScreen,
        onPurchasesScreen = onPurchasesScreen,
    )
}