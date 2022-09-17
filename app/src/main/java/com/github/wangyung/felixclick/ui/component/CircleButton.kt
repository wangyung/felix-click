package com.github.wangyung.felixclick.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.wangyung.felixclick.ui.theme.ButtonFontSize

@Composable
fun CircleButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val haptic = LocalHapticFeedback.current
    Button(
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick.invoke()
        }, shape = CircleShape, modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = ButtonFontSize,
        )
    }
}

@Preview
@Composable
fun CircleButtonPreview() {
    CircleButton(text = "9", modifier = Modifier.size(50.dp))
}