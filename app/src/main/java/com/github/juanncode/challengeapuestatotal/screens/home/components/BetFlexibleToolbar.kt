@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.juanncode.challengeapuestatotal.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.juanncode.challengeapuestatotal.R
import com.github.juanncode.challengeapuestatotal.components.FlexibleTopBar
import com.github.juanncode.challengeapuestatotal.components.FlexibleTopBarDefaults
import com.github.juanncode.challengeapuestatotal.ui.theme.Poppins

@Composable
fun BetFlexibleToolbar(
    selectedValueType: String,
    selectedValueStatus: String,
    optionsStatus: List<String>,
    optionsType: List<String>,
    stateTextFieldState: TextFieldState,
    onValueChangedEventType: (String) -> Unit,
    onValueChangedEventStatus: (String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
) {
    var isDropDownOpenType by rememberSaveable {
        mutableStateOf(false)
    }

    var isDropDownOpenStatus by rememberSaveable {
        mutableStateOf(false)
    }

    FlexibleTopBar(
        scrollBehavior = scrollBehavior,
        colors = FlexibleTopBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.bet_logo),
                    contentDescription = "bet",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.bet_challenge),
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = Poppins
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(
                            id = R.string.search
                        ),
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "Carlos", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Box {
                    DropdownMenu(
                        expanded = isDropDownOpenType,
                        onDismissRequest = { isDropDownOpenType = false }) {
                        optionsType.forEach {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        isDropDownOpenType = false
                                        onValueChangedEventType(it)
                                    }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                Text(text = it)
                            }
                        }

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = R.string.label_type), fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        FilledTonalButton(
                            onClick = { isDropDownOpenType = true },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(text = selectedValueType)
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = stringResource(
                                    id = R.string.bet_id
                                ),
                                tint = Color.White
                            )
                        }
                    }
                }
                Box {
                    DropdownMenu(
                        expanded = isDropDownOpenStatus,
                        onDismissRequest = { isDropDownOpenStatus = false }) {
                        optionsStatus.forEach {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clickable {
                                        isDropDownOpenStatus = false
                                        onValueChangedEventStatus(it)
                                    }
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                Text(text = it)
                            }
                        }

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(text = stringResource(id = R.string.label_status), fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        FilledTonalButton(
                            onClick = { isDropDownOpenStatus = true },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )

                        ) {
                            Text(text = selectedValueStatus)
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = stringResource(
                                    id = R.string.bet_id
                                ),
                                tint = Color.White
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(3.dp))
            BetTextField(
                state = stateTextFieldState,
                startIcon = Icons.Default.Search,
                hint = stringResource(
                    id = R.string.search_bet
                )
            )
        }
    }

}

@Preview
@Composable
private fun BetFlexibleToolbarPreview() {
    BetFlexibleToolbar(
        onValueChangedEventType = {

        },
        onValueChangedEventStatus = {

        },

        selectedValueType = "asd",
        selectedValueStatus = "asd",
        optionsStatus = listOf("asdasd", "asdasd"),
        optionsType = listOf("asdasd", "asdasd"),
        stateTextFieldState = rememberTextFieldState()
    )
}