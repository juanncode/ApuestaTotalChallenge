package com.github.juanncode.challengeapuestatotal.domain.repository

import com.github.juanncode.challengeapuestatotal.domain.entity.User
import com.github.juanncode.challengeapuestatotal.utils.Resource

interface UserRepository {
    suspend fun loginUser(): Resource<User>
}