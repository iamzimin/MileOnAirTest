package com.evg.mileonairtest

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Settings: Route
    @Serializable data object Registration: Route
    @Serializable data object Purchases: Route
}
