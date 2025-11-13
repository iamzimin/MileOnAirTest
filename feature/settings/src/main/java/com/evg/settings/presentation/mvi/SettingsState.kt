package com.evg.settings.presentation.mvi

import com.evg.settings.data.model.SettingsUser

data class SettingsState(
    val settingsUser: SettingsUser? = null,
)