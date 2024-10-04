package com.github.juanncode.challengeapuestatotal

import kotlinx.serialization.Serializable

sealed class AppRouter {

    @Serializable
    data object LoginRoute: AppRouter()
    @Serializable
    data object HomeRoute: AppRouter()
    @Serializable
    data class DetailRoute(val id: String) : AppRouter()
}