package com.evg.settings.data.repository

import com.evg.settings.data.model.SettingsUser
import com.evg.settings.domain.repository.SettingsRepository
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository

class SettingsRepositoryImpl(
    private val sharedPrefsRepository: SharedPrefsRepository,
) : SettingsRepository {

    override suspend fun getUser(): SettingsUser? {
        val sharedPrefsUser = sharedPrefsRepository.getNameAndSurname() ?: return null
        
        return SettingsUser(
            name = sharedPrefsUser.first,
            surname = sharedPrefsUser.second,
        )
    }
}