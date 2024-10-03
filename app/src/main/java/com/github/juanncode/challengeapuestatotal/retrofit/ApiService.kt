package com.github.juanncode.challengeapuestatotal.retrofit

import com.github.juanncode.challengeapuestatotal.retrofit.model.BetDetailModel
import com.github.juanncode.challengeapuestatotal.retrofit.model.BetModel
import retrofit2.http.GET

interface ApiService {
    @GET("bets")
    suspend fun getBets(): List<BetModel>

    @GET("detail-bets")
    suspend fun getDetailBets(): List<BetDetailModel>
}