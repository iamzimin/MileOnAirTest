package com.evg.registration.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.orbitmvi.orbit.compose.collectAsState
import com.evg.registration.presentation.mvi.RegistrationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationRoot(
    modifier: Modifier,
    viewModel: RegistrationViewModel = koinViewModel(),
    onPreviousScreen: () -> Unit,
) {
    RegistrationScreen(
        modifier = modifier,
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
        onPreviousScreen = onPreviousScreen,
    )
}