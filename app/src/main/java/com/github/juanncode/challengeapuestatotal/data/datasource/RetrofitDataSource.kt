package com.github.juanncode.challengeapuestatotal.data.datasource

import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.mappers.toDomain
import com.github.juanncode.challengeapuestatotal.retrofit.ApiService
import com.github.juanncode.challengeapuestatotal.utils.Resource
import com.github.juanncode.challengeapuestatotal.utils.safeApiCall
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource{
    override suspend fun getBets(): Resource<List<Bet>> {
        return safeApiCall {
            apiService.getBets().map { it.toDomain() }
        }
    }

    override suspend fun getDetailBets(): Resource<List<BetDetail>> {
        return  safeApiCall {
            apiService.getDetailBets().map { it.toDomain() }
        }
    }
}