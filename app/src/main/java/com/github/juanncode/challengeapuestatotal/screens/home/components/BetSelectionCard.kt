package com.github.juanncode.challengeapuestatotal.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.juanncode.challengeapuestatotal.R
import com.github.juanncode.challengeapuestatotal.domain.entity.BetSelection

@Composable
fun BetSelectionCard(
    modifier: Modifier = Modifier, betSelection: BetSelection
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f))
            .padding(horizontal = 14.dp, vertical = 6.dp)


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${betSelection.MarketName} ${betSelection.Name}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = betSelection.EventName, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.width(5.dp))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = betSelection.Price,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,

                    )


                Spacer(modifier = Modifier.height(3.dp))
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                            RoundedCornerShape(12.dp)

                        )
                        .padding(horizontal = 7.dp, vertical = 2.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            modifier = Modifier.size(14.dp),
                            contentDescription = stringResource(id = R.string.date)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = betSelection.EventDate,
                            fontSize = 12.sp,
                        )
                    }
                }

            }

        }
    }

}

@Preview
@Composable
private fun BetSelectionCardPreview() {
    BetSelectionCard(betSelection = BetSelection(
        SelectionId = 1811160742,
        SelectionStatus = 0,
    Price = "1.23",
    Name = "Real Madrid",
    Spec = "",
    MarketTypeId = 1,
    MarketId = 775746628,
    MarketName = "1x2",
    IsLive = false,
    IsBetBuilder = false,
    IsBanker = false,
    IsVirtual = false,
    BBSelections = mutableListOf(),
    EventId = 9996710,
    EventCode = null,
    FeedEventId = 10390066,
    EventName = "Real Madrid vs. Alavés ",
    SportTypeId = 1,
    CategoryId = 501,
    CategoryName = null,
    ChampId = 2941,
    ChampName = null,
    EventScore = "",
    GameTime = "",
    EventDate = "24/09/24 - 14:00",
    PitcherInfo = null,
    Runners = null,
    ExtraEventInfo = null,
    RC = false,
    LiveInfoAtEventMinute = null,
    IsLiveOrVirtual = false,
    EarlyPayout = true,
    BoreDraw = false,
    DeadHeatFactor = null,
    DbId = 10
    ))
}