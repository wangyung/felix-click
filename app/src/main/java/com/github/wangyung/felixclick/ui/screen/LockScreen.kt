package com.github.wangyung.felixclick.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.wangyung.felixclick.R
import com.github.wangyung.felixclick.ui.theme.FelixClickTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LockScreen(
    modifier: Modifier = Modifier,
    lockState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
) {
    var password by remember {
        mutableStateOf("")
    }
    if (password == "3064") {
        val keyboardController = LocalSoftwareKeyboardController.current
        keyboardController?.hide()
        lockState.value = false
//        MainScreen(modifier = Modifier.fillMaxSize())
    } else {
        Box(modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Icon(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.icon_lock),
                    contentDescription = null
                )
                Text(text = "Password:")
                TextField(
                    value = password,
                    onValueChange = { password = it }
                )
            }
        }
    }
}

@Preview
@Composable
fun LockScreenPreview() {
    FelixClickTheme {
        LockScreen()
    }
}