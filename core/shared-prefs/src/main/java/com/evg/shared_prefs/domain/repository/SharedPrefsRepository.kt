package com.evg.shared_prefs.domain.repository

import com.evg.shared_prefs.domain.model.User

interface SharedPrefsRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User?
    suspend fun getNameAndSurname(): Pair<String, String>?
}