@file:OptIn(FlowPreview::class)

package com.github.juanncode.challengeapuestatotal.screens.home

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.repository.BetRepository
import com.github.juanncode.challengeapuestatotal.utils.Resource
import com.github.juanncode.challengeapuestatotal.utils.convertStatusToValid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val betRepository: BetRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    val optionsStatus = listOf("Todos", "Ganado", "Rechazado", "Perdido", "Abierto", "Cashout")
    val optionsType = listOf("Todos", "Simple", "System")


    init {
        viewModelScope.launch {
            state = state.copy(loading = true)
            delay(1000)
            val responseBets = betRepository.getBets()
            val responseBetsDetail = betRepository.getDetailBets()
            state = state.copy(loading = false)

            if (responseBets is Resource.Error) {
                state = state.copy(error = responseBets.error)
                return@launch
            }

            if (responseBetsDetail is Resource.Error) {
                state = state.copy(error = responseBetsDetail.error)
                return@launch
            }

            val bets = responseBets as Resource.Success
            val betsDetail = responseBetsDetail as Resource.Success

            state = state.copy(
                bets = bets.value,
                detailBets = betsDetail.value,
                betsBackup = bets.value,
                detailBetsBackup = betsDetail.value,
                optionsStatus = optionsStatus,
                optionsType = optionsType
            )

            snapshotFlow {
                state.textFieldState.text
            }.debounce(300L).collectLatest { query ->
                filterByQuery(query)
            }
        }

    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.CleanError -> state = state.copy(error = null)
            is HomeEvent.FilterByStatus -> filterByStatus(event)
            is HomeEvent.FilterByType -> filterByType(event)
        }
    }

    private fun filterByType(event: HomeEvent.FilterByType) {
        var bets: List<Bet>
        if (event.type == "Todos" && state.selectedValueStatus == "Todos") {
            state = state.copy(bets = state.betsBackup, selectedValueType = event.type)
            return
        }

        bets = if (event.type == "Todos") {
            state.betsBackup
        } else {
            state.betsBackup.filter { it.type == event.type.uppercase() }
        }

        if (state.selectedValueStatus != "Todos") {
            bets =
                bets.filter { it.status == convertStatusToValid(state.selectedValueStatus) }
        }

        state = state.copy(bets = bets, selectedValueType = event.type)

        state.textFieldState.clearText()
    }

    private fun filterByStatus(event: HomeEvent.FilterByStatus) {
        var bets: List<Bet>
        if (event.status == "Todos" && state.selectedValueType == "Todos") {
            state = state.copy(bets = state.betsBackup, selectedValueStatus = event.status)
            return
        }
        bets = if (event.status == "Todos") {
            state.betsBackup
        } else {
            state.betsBackup.filter { it.status == convertStatusToValid(event.status) }
        }
        if (state.selectedValueType != "Todos") {
            bets = bets.filter { it.type == state.selectedValueType.uppercase() }
        }
        state = state.copy(bets = bets, selectedValueStatus = event.status)

        state.textFieldState.clearText()
    }

    private fun filterByQuery(query: CharSequence) {
        if (query.isEmpty() && state.bets == state.betsBackup) return
        if (query.isEmpty()) {
            if (state.selectedValueType == "Todos" && state.selectedValueStatus == "Todos") {
                state = state.copy(bets = state.betsBackup)
            }
            return
        }

        val betsByName = state.detailBetsBackup.filter {
            it.BetSelections.any { bs ->
                bs.EventName.lowercase().contains(query.toString().lowercase().trim())
            }
        }
        state = state.copy(
            bets = state.betsBackup.filter { bet ->
                betsByName.any { betDetail ->
                    bet.game == betDetail.BetId.toString()
                }
            }.toList(),
            selectedValueType = "Todos", selectedValueStatus = "Todos"
        )

    }
}