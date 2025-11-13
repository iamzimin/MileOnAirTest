package com.evg.registration.domain.mapper

import com.evg.registration.domain.model.RegistrationUser
import com.evg.shared_prefs.domain.model.User

fun RegistrationUser.toUser(): User {
    return User(
        name = this.name,
        surname = this.surname,
        participantNumber = this.participantNumber,
        code = this.code,
    )
}

fun User.toRegistrationUser(): RegistrationUser {
    return RegistrationUser(
        name = this.name,
        surname = this.surname,
        participantNumber = this.participantNumber,
        code = this.code,
    )
}