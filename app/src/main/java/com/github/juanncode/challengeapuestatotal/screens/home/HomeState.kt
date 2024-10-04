package com.github.juanncode.challengeapuestatotal.screens.home

import androidx.compose.foundation.text.input.TextFieldState
import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.utils.ErrorGeneric

data class HomeState(
    val loading: Boolean = false,
    val error: ErrorGeneric? = null,
    val optionsType: List<String> = emptyList(),
    val optionsStatus: List<String> = emptyList(),
    val selectedValueType: String = "Todos",
    val selectedValueStatus: String = "Todos",
    val textFieldState: TextFieldState = TextFieldState(),
    val bets: List<Bet> = emptyList(),
    val detailBets: List<BetDetail> = emptyList(),
    val betsBackup: List<Bet> = emptyList(),
    val detailBetsBackup: List<BetDetail> = emptyList(),
)
