package com.evg.settings.di

import com.evg.settings.data.repository.SettingsRepositoryImpl
import com.evg.settings.domain.repository.SettingsRepository
import com.evg.settings.presentation.mvi.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            //context = get(),
        )
    }
    viewModel { SettingsViewModel(settingsRepository = get()) }
}