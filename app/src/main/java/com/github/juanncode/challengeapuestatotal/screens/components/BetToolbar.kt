@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.juanncode.challengeapuestatotal.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.juanncode.challengeapuestatotal.R
import com.github.juanncode.challengeapuestatotal.ui.theme.ArrowLeftIcon
import com.github.juanncode.challengeapuestatotal.ui.theme.ChallengeApuestaTotalTheme
import com.github.juanncode.challengeapuestatotal.ui.theme.Poppins

@Composable
fun BetToolbar(
    modifier: Modifier = Modifier,
    showBackButton: Boolean,
    title: String,
    onMenuItemClick: (Int) -> Unit = {},
    onBackClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    startContent: (@Composable () -> Unit)? = null

) {
    LargeTopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White,
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.White
        ),
        title = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    startContent?.invoke()
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = Poppins
                    )
                }
                Row {
                    FilterChip(
                        selected = false,
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = "Open")
                        },
                    )
                    FilterChip(
                        selected = false,
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = "Open")
                        },
                    )
                    FilterChip(
                        selected = true,
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = "Open")
                        },
                    )
                }
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ArrowLeftIcon,
                        contentDescription = stringResource(id = R.string.go_back),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(
                        id = R.string.bet
                    ),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

    )
}

@Preview
@Composable
private fun PreviewCorrerToolbar() {
    ChallengeApuestaTotalTheme {
        BetToolbar(showBackButton = true, title = "Bet", startContent = {
            Image(
                painter = painterResource(id = R.drawable.bet_logo),
                contentDescription = "bet"
            )
        })
    }

}