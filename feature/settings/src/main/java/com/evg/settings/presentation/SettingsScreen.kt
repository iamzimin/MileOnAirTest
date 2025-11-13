package com.evg.settings.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.evg.resource.theme.MileOnAirTestTheme
import com.evg.settings.presentation.mvi.SettingsState
import com.evg.settings.presentation.mvi.SettingsAction

@Composable
fun SettingsScreen(
    modifier: Modifier,
    state: SettingsState,
    dispatch: (SettingsAction) -> Unit,
) {

}

@Preview
@Composable
private fun SettingsScreenPreview() {
    MileOnAirTestTheme {
        SettingsScreen(
            modifier = Modifier,
            state = SettingsState(),
            dispatch = {},
        )
    }
}