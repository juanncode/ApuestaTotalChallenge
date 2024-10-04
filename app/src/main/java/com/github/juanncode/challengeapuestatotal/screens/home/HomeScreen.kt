@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.juanncode.challengeapuestatotal.screens.home

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.juanncode.challengeapuestatotal.R
import com.github.juanncode.challengeapuestatotal.components.GradientBackground
import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.screens.home.components.BetChip
import com.github.juanncode.challengeapuestatotal.screens.home.components.BetFlexibleToolbar
import com.github.juanncode.challengeapuestatotal.screens.home.components.BetSelectionCard
import com.github.juanncode.challengeapuestatotal.screens.home.components.BoxText
import com.github.juanncode.challengeapuestatotal.ui.theme.BetDarkRed
import com.github.juanncode.challengeapuestatotal.ui.theme.BetGolden
import com.github.juanncode.challengeapuestatotal.ui.theme.ChallengeApuestaTotalTheme
import com.github.juanncode.challengeapuestatotal.utils.getColorByChipStatus
import com.github.juanncode.challengeapuestatotal.utils.getColorByLevel


@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    navController: NavController,
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(
                context,
                state.error.message,
                Toast.LENGTH_LONG
            ).show()
            onEvent(HomeEvent.CleanError)
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            BetFlexibleToolbar(
                scrollBehavior = scrollBehavior,
                optionsStatus = state.optionsStatus,
                selectedValueType = state.selectedValueType,
                selectedValueStatus = state.selectedValueStatus,
                onValueChangedEventType = {
                    onEvent(HomeEvent.FilterByType(it))
                },
                onValueChangedEventStatus = {
                    onEvent(HomeEvent.FilterByStatus(it))
                },
                optionsType = state.optionsType,
                stateTextFieldState = state.textFieldState
            )
        }
    ) {
        GradientBackground {
            Box(
                Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    }

            ) {

                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .padding(horizontal = 15.dp)

                ) {

                    if (state.loading.not() && state.bets.isEmpty()) {
                        item {
                            Text(
                                modifier = Modifier.padding(vertical = 20.dp),
                                text = stringResource(id = R.string.result_empty),
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                    items(
                        state.bets,
                        key = { it.game }
                    ) {
                        BetItem(
                            modifier = Modifier.animateItem(),
                            bet = it,
                            betDetail = state.detailBets.first { bd ->
                                it.game == bd.BetId.toString()
                            }
                        )
                    }
                }


                if (state.loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = BetGolden
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ChallengeApuestaTotalTheme {
        HomeScreen(
            state = HomeState(),
            onEvent = {},
            navController = rememberNavController()
        )
    }
}

@Composable
fun BetItem(modifier: Modifier, bet: Bet, betDetail: BetDetail) {
    var expandedState by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            ),
        color = getColorByLevel(betDetail.BetNivel).copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(getColorByLevel(betDetail.BetNivel).copy(alpha = 0.8f))
                    .padding(horizontal = 15.dp, vertical = 10.dp),

                ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(text = bet.type, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                        Text(text = bet.game, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    }
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        BetChip(
                            value = bet.status,
                            color = getColorByChipStatus(bet.status),
                            textColor = Color.White
                        )
                        Text(text = bet.createdDate, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BoxText(
                    title = stringResource(id = R.string.bet),
                    subTitle = "S/${betDetail.TotalStake}",
                )

                BoxText(
                    title = stringResource(id = R.string.total_share),
                    subTitle = betDetail.TotalOdds,
                )
                BoxText(
                    title = stringResource(id = R.string.revenue),
                    subTitle = betDetail.TotalWin,
                    colorSubtitle = getColorByChipStatus(bet.status)
                )
            }

            Row {
                Spacer(modifier = Modifier.weight(1f))
                FilledTonalButton(
                    onClick = {
                        expandedState = !expandedState
                    },
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                ) {
                    Text(
                        text = stringResource(id = if (!expandedState) R.string.see_more else R.string.see_less),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = if (!expandedState) R.drawable.keyboard_arrow_down else R.drawable.keyboard_arrow_up),
                        contentDescription = stringResource(
                            id = if (expandedState) R.string.keyboard_arrow_down else R.string.keyboard_arrow_up

                        ),
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }

            if (expandedState)
                betDetail.BetSelections.forEach {
                    BetSelectionCard(betSelection = it)
                }
            if (bet.status == "OPEN")
                Row {
                    val colorStops = arrayOf(
                        0.0f to BetDarkRed,
                        1f to Color.Red
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(colorStops = colorStops)
                            )
                            .clickable { }
                            .padding(horizontal = 10.dp, vertical = 5.dp)


                    ) {
                        Text(text = "Cashout S/${betDetail.CashoutValue}")
                    }
                }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
private fun BetItemPreview() {
    BetItem(
        bet = Bet(
            db = 4,
            operation = 881854985,
            game = "2988647420",
            createdDate = "2024-09-24 15:39:55",
            status = "OPEN",
            wager = 300,
            winning = null,
            odds = 22.4663,
            type = "SYSTEM",
            account = "CASH"
        ),
        betDetail = BetDetail(
            BetNivel = "Donatelo",
            BetStarts = 0,
            BetStatusName = "Abierto",
            BetTypeName = "System",
            BgSrc = "/_next/static/media/cazafijas.47b1d146.jpg",
            CashoutOdds = "0.00",
            TotalOdds = "0.00",
            TotalStake = "3.00",
            TotalWin = "38.33",
            CashoutValue = "0.80",
            CreatedDate = "24/09/24 - 10:39",
            BetStatus = 0,
            BetType = 2,
            BetId = 2988647420,
            BetSelections = mutableListOf()
        ),
        modifier = Modifier
    )
}