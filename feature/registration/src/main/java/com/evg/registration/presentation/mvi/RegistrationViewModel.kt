package com.evg.registration.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.registration.domain.model.RegistrationUser
import com.evg.registration.domain.repository.RegistrationRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository,
) : ContainerHost<RegistrationState, RegistrationSideEffect>, ViewModel() {
    override val container = container<RegistrationState, RegistrationSideEffect>(RegistrationState()) {
        loadUser()
    }

    fun dispatch(action: RegistrationAction) {
        when (action) {
            is RegistrationAction.LoadUser -> loadUser()
            is RegistrationAction.SaveUser -> saveUser(action.user)
        }
    }

    private fun loadUser() = intent {
        val user = registrationRepository.getUser()
        reduce { state.copy(registrationUser = user) }
    }

    private fun saveUser(user: RegistrationUser) = intent {
        registrationRepository.saveUser(user)
    }
}