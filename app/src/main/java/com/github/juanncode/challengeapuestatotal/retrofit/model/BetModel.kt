package com.github.juanncode.challengeapuestatotal.retrofit.model

import kotlinx.serialization.SerialName

data class BetModel (
    val db: Long,
    val operation: Long,
    val game: String,

    val created_date: String,

    val status: String,
    val wager: Long,
    val winning: Long? = null,
    val odds: Double,
    val type: String,
    val account: String
)

