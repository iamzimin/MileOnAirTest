package com.evg.registration.domain.repository

import com.evg.registration.domain.model.RegistrationUser

interface RegistrationRepository {
    suspend fun getUser(): RegistrationUser?
    suspend fun saveUser(user: RegistrationUser)
}