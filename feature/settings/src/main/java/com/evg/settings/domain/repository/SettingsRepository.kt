package com.evg.settings.domain.repository

import com.evg.settings.data.model.SettingsUser

interface SettingsRepository {
    suspend fun getUser(): SettingsUser?
}