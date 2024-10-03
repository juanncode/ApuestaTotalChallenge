package com.github.juanncode.challengeapuestatotal.data.repository

import com.github.juanncode.challengeapuestatotal.data.datasource.RemoteDataSource
import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.domain.repository.BetRepository
import com.github.juanncode.challengeapuestatotal.utils.Resource
import javax.inject.Inject

class BetRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BetRepository{
    override suspend fun getBets(): Resource<List<Bet>> {
        return remoteDataSource.getBets()
    }

    override suspend fun getDetailBets(): Resource<List<BetDetail>> {
        return remoteDataSource.getDetailBets()

    }
}