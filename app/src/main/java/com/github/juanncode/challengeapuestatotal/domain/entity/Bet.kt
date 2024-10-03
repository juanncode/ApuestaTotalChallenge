package com.github.juanncode.challengeapuestatotal.domain.entity


data class Bet (
    val db: Long,
    val operation: Long,
    val game: String,
    val createdDate: String,
    val status: String,
    val wager: Long,
    val winning: Long? = null,
    val odds: Double,
    val type: String,
    val account: String
)