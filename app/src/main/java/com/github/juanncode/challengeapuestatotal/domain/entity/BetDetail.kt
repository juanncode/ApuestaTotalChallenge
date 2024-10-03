package com.github.juanncode.challengeapuestatotal.domain.entity

data class BetDetail(
    val BetId: Long,
    val BetNivel: String,
    val BetSelections: List<BetSelection>,
    val BetStarts: Int,
    val BetStatus: Int,
    val BetStatusName: String,
    val BetType: Int,
    val BetTypeName: String,
    val BgSrc: String,
    val CashoutOdds: String,
    val CashoutValue: String,
    val CreatedDate: String,
    val TotalOdds: String,
    val TotalStake: String,
    val TotalWin: String
)