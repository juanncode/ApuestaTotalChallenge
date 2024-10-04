package com.github.juanncode.challengeapuestatotal.screens.login

import com.github.juanncode.challengeapuestatotal.screens.home.HomeEvent

sealed interface LoginEvent {
    data object OnTogglePasswordVisibilityClick: LoginEvent
    data object OnLoginClick: LoginEvent
    data object CleanError: LoginEvent

}