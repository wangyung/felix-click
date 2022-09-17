package com.github.wangyung.felixclick.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.wangyung.felixclick.R
import com.github.wangyung.felixclick.ui.component.CircleButton
import com.github.wangyung.felixclick.ui.component.FrontTruncatedTextView
import com.github.wangyung.felixclick.ui.component.LongPressableButton
import com.github.wangyung.felixclick.ui.theme.ButtonSize
import com.github.wangyung.felixclick.ui.theme.FelixClickTheme
import com.github.wangyung.felixclick.ui.theme.Grey200

@Composable
fun MainScreen(modifier: Modifier = Modifier, launchedTime: Long = System.currentTimeMillis()) {
    Column(modifier = modifier.padding(12.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        val inputTextBuilder = remember { mutableStateOf("") }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .border(width = 1.dp, brush = SolidColor(Grey200), shape = RoundedCornerShape(4.dp))
                .clip(RoundedCornerShape(size = 4.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            FrontTruncatedTextView(
                text = inputTextBuilder.value,
                fontSize = 36.sp,
                modifier = Modifier
                    .padding(start = 12.dp, end = 4.dp)
                    .weight(1f)
            )
            val hapticFeedback = LocalHapticFeedback.current
            LongPressableButton(
                modifier = Modifier.size(56.dp),
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    inputTextBuilder.value = inputTextBuilder.value.dropLast(1)
                },
                onLongPress = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    inputTextBuilder.value = ""
                }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_backspace),
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RangedRowButtons(intRange = 7..9, inputState = inputTextBuilder)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RangedRowButtons(4..6, inputTextBuilder)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RangedRowButtons(intRange = 1..3, inputState = inputTextBuilder)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CircleButton(
                text = "#",
                modifier = Modifier.size(ButtonSize),
                onClick = { inputTextBuilder.value += "#" }
            )
            CircleButton(
                text = "0",
                modifier = Modifier.size(ButtonSize),
                onClick = { inputTextBuilder.value += "0" }
            )
            CircleButton(
                text = "*",
                modifier = Modifier.size(ButtonSize),
                onClick = { inputTextBuilder.value += "*" }
            )
        }
    }
}

@Composable
private fun RangedRowButtons(intRange: IntRange, inputState: MutableState<String>) {
    for (i in intRange) {
        val text = i.toString()
        CircleButton(
            text = text,
            modifier = Modifier.size(ButtonSize),
            onClick = { inputState.value += text }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FelixClickTheme {
        MainScreen()
    }
}
