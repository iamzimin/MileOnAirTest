package com.evg.registration.presentation.mvi

import com.evg.registration.domain.model.RegistrationUser

sealed class RegistrationAction {
    data object LoadUser : RegistrationAction()
    data class SaveUser(val user: RegistrationUser) : RegistrationAction()
}