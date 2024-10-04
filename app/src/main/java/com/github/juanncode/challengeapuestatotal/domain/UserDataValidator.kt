package com.github.juanncode.challengeapuestatotal.domain

import android.util.Patterns

class UserDataValidator {

    fun isValidEmail(email: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }
    companion object {
        const val MIN_PASSWORD_LENGTH = 1
    }
}