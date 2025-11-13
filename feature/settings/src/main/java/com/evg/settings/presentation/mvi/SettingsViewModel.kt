package com.evg.settings.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.settings.domain.repository.SettingsRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ContainerHost<SettingsState, SettingsSideEffect>, ViewModel() {
    override val container = container<SettingsState, SettingsSideEffect>(SettingsState()) {
        loadUser()
    }

    fun dispatch(action: SettingsAction) {
        when (action) {
            is SettingsAction.LoadUser -> loadUser()
        }
    }

    private fun loadUser() = intent {
        val user = settingsRepository.getUser()
        reduce { state.copy(settingsUser = user) }
    }
}