package com.github.juanncode.challengeapuestatotal.domain.repository

import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.utils.Resource

interface BetRepository {
    suspend fun getBets(): Resource<List<Bet>>
    suspend fun getDetailBets(): Resource<List<BetDetail>>
}