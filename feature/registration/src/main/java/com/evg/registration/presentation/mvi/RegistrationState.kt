package com.evg.registration.presentation.mvi

import com.evg.registration.domain.model.RegistrationUser

data class RegistrationState(
    val registrationUser: RegistrationUser? = null,
)