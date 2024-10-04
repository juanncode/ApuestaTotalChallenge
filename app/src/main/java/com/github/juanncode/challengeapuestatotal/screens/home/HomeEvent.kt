package com.github.juanncode.challengeapuestatotal.screens.home

sealed interface HomeEvent {

    data object CleanError: HomeEvent
    data class FilterByType(val type: String): HomeEvent
    data class FilterByStatus(val status: String): HomeEvent
}