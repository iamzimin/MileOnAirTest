package com.evg.registration.di

import com.evg.registration.data.repository.RegistrationRepositoryImpl
import com.evg.registration.domain.repository.RegistrationRepository
import com.evg.registration.presentation.mvi.RegistrationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val registrationModule = module {
    single<RegistrationRepository> {
        RegistrationRepositoryImpl(
            sharedPrefsRepository = get()
        )
    }
    viewModel { RegistrationViewModel(registrationRepository = get()) }
}