package com.github.juanncode.challengeapuestatotal.data.datasource

import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.domain.entity.User
import com.github.juanncode.challengeapuestatotal.utils.Resource

interface RemoteDataSource {

    suspend fun getBets(): Resource<List<Bet>>
    suspend fun getDetailBets(): Resource<List<BetDetail>>

    suspend fun getUser(): Resource<User>
}