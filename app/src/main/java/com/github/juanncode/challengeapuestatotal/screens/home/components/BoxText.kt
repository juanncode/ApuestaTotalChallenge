package com.github.juanncode.challengeapuestatotal.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoxText(
    title: String,
    subTitle: String,
    colorSubtitle: Color = MaterialTheme.colorScheme.onSurface,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.W400, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = subTitle, fontWeight = FontWeight.SemiBold, color = colorSubtitle)
    }
}

@Preview
@Composable
private fun BoxTextPreview() {
    BoxText(title = "id apuesta", subTitle = "123123", colorSubtitle = Color.Black)
}