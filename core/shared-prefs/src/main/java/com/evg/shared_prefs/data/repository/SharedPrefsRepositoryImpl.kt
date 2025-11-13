package com.evg.shared_prefs.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.evg.shared_prefs.domain.model.User
import com.evg.shared_prefs.domain.repository.SharedPrefsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPrefsRepositoryImpl(
    context: Context,
) : SharedPrefsRepository {
    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_NAME = "user_name"
        private const val KEY_SURNAME = "user_surname"
        private const val KEY_CODE = "user_code"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    override suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putString(KEY_NAME, user.name)
                putString(KEY_SURNAME, user.surname)
                putLong(KEY_CODE, user.code)
                apply()
            }
        }
    }

    override suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        val name = sharedPreferences.getString(KEY_NAME, null)
        val surname = sharedPreferences.getString(KEY_SURNAME, null)
        val code = sharedPreferences.getLong(KEY_CODE, 0)

        if (name == null && surname == null && code == 0L) {
            null
        } else {
            User(
                name = name ?: "",
                surname = surname ?: "",
                code = code,
            )
        }
    }

    override suspend fun getNameAndSurname(): Pair<String, String>? = withContext(Dispatchers.IO) {
        val name = sharedPreferences.getString(KEY_NAME, null)
        val surname = sharedPreferences.getString(KEY_SURNAME, null)

        if (name == null && surname == null) {
            null
        } else {
            Pair(name ?: "", surname ?: "")
        }
    }
}