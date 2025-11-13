package com.evg.settings.presentation.mvi

sealed class SettingsAction {
    data object LoadUser : SettingsAction()
}