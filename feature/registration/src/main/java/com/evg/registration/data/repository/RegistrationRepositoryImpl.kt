package com.evg.registration.data.repository

import com.evg.registration.domain.mapper.toRegistrationUser
import com.evg.registration.domain.mapper.toUser
import com.evg.registration.domain.model.RegistrationUser
import com.evg.registration.domain.repository.RegistrationRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository

class RegistrationRepositoryImpl(
    private val sharedPrefsRepository: SharedPrefsRepository,
) : RegistrationRepository {

    override suspend fun getUser(): RegistrationUser? {
        val sharedPrefsUser = sharedPrefsRepository.getUser() ?: return null
        return sharedPrefsUser.toRegistrationUser()
    }

    override suspend fun saveUser(user: RegistrationUser) {
        sharedPrefsRepository.saveUser(user.toUser())
    }
}