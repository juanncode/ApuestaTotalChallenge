package com.github.juanncode.challengeapuestatotal.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BetChip(
    modifier: Modifier = Modifier,
    value: String,
    color: Color,
    textColor: Color
) {
    Box(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(color = color)
        .padding(vertical = 1.dp, horizontal = 6.dp)
    ) {
        Text(text = value, color = textColor, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview
@Composable
private fun BetChipPreview() {
    BetChip(
        value = "Cashout",
        color = Color.Red,
        textColor = Color.White
    )
}