package com.github.juanncode.challengeapuestatotal.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.challengeapuestatotal.domain.UserDataValidator
import com.github.juanncode.challengeapuestatotal.domain.repository.UserRepository
import com.github.juanncode.challengeapuestatotal.utils.ErrorGeneric
import com.github.juanncode.challengeapuestatotal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataValidator: UserDataValidator,
    private val userRepository: UserRepository
): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    init {
        snapshotFlow {
            state.email.text
        }.onEach { email ->
            if (email.isEmpty()) return@onEach
            val isValidEmail = userDataValidator.isValidEmail(email.toString())

            state = state.copy(
                isEmailValid = isValidEmail,
                canLogin = isValidEmail && state.isPasswordValid
            )
        }.launchIn(viewModelScope)

        snapshotFlow {
            state.password.text
        }.onEach { password ->
            println(password)
            println(state.email)
            val isValidPassword = userDataValidator.isValidPassword(password.toString())
            val isValidEmail = userDataValidator.isValidEmail(state.email.text.toString())
            state = state.copy(
                isPasswordValid = isValidPassword,
                canLogin = isValidEmail && isValidPassword
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.OnLoginClick -> {
                login()
            }
            LoginEvent.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }

            LoginEvent.CleanError -> {
                state = state.copy(error = null)
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            delay(1000)
            val result = userRepository.loginUser()
            state = state.copy(isLoggingIn = false)

            when(result) {
                is Resource.Error -> {
                    println(result.error)
                    state = state.copy(error = result.error)
                }
                is Resource.Success -> {
                    if (result.value.correo == state.email.text && result.value.password == state.password.text)
                        state = state.copy(success = true)
                    else
                        state = state.copy(error = ErrorGeneric(
                            error = "Correo o contraseña incorrectos",
                            code = 0,
                            message = "Correo o contraseña incorrectos"
                        ))

                }
            }
        }
    }
}