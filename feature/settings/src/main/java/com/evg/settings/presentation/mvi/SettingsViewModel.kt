package com.evg.settings.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.settings.domain.repository.SettingsRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ContainerHost<SettingsState, SettingsSideEffect>, ViewModel() {
    override val container = container<SettingsState, SettingsSideEffect>(SettingsState())

    fun dispatch(action: SettingsAction) {
        when (action) {
            is SettingsAction.FirstClass -> test()
            is SettingsAction.SecondObject -> test()
        }
    }

    private fun test() = intent {
        //reduce { state.copy(variable = true) }
    }
}