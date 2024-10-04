package com.github.juanncode.challengeapuestatotal.utils

import androidx.compose.ui.graphics.Color
import com.github.juanncode.challengeapuestatotal.ui.theme.BetBlue
import com.github.juanncode.challengeapuestatotal.ui.theme.BetDarkRed
import com.github.juanncode.challengeapuestatotal.ui.theme.BetGolden
import com.github.juanncode.challengeapuestatotal.ui.theme.BetGray
import com.github.juanncode.challengeapuestatotal.ui.theme.BetOrange
import com.github.juanncode.challengeapuestatotal.ui.theme.BetRed
import com.github.juanncode.challengeapuestatotal.ui.theme.BetSilver
import com.github.juanncode.challengeapuestatotal.ui.theme.BetWhite

fun getColorByChipStatus(status: String): Color {
    return when (status) {
        "OPEN" -> BetGray
        "WON" -> BetGolden
        "LOST" -> BetRed
        else -> Color.Gray

    }
}

fun getColorByLevel(level: String): Color {
    return when (level) {
        "Donatelo" -> BetBlue
        "Capo" -> BetGolden
        "Leyenda" -> BetDarkRed
        "King" -> BetSilver
        "Master" -> BetGray
        "Cazafijas" -> BetOrange
        "LOST" -> BetRed
        else -> Color.Gray

    }
}

fun convertStatusToValid(status: String): String {
    when (status) {
        "Abierto" -> return "OPEN"
        "Ganado" -> return "WON"
        "Perdido" -> return "LOST"
        else -> return ""
    }
}