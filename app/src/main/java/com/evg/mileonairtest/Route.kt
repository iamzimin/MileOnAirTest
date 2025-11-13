package com.evg.mileonairtest

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Settings: Route
}
