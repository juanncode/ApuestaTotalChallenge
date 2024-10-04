package com.github.juanncode.challengeapuestatotal.data.repository

import com.github.juanncode.challengeapuestatotal.data.datasource.RemoteDataSource
import com.github.juanncode.challengeapuestatotal.domain.entity.User
import com.github.juanncode.challengeapuestatotal.domain.repository.UserRepository
import com.github.juanncode.challengeapuestatotal.utils.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository{
    override suspend fun loginUser(): Resource<User> {
        return remoteDataSource.getUser()
    }

}