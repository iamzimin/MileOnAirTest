package com.evg.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.orbitmvi.orbit.compose.collectAsState
import com.evg.settings.presentation.mvi.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsRoot(
    modifier: Modifier,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    /*viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is SettingsSideEffect.FirstClass -> {
                when (sideEffect.paramOne) {
                    "" -> {}
                }
            }
            SettingsSideEffect.FirstObject -> {}
        }
    }*/

    SettingsScreen(
        modifier = modifier,
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
    )
}