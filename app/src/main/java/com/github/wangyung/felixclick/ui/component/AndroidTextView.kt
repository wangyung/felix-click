package com.github.wangyung.felixclick.ui.component

import android.text.TextUtils
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun FrontTruncatedTextView(text: String, fontSize: TextUnit, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                maxLines = 1
                ellipsize = TextUtils.TruncateAt.START
                textSize = fontSize.value
            }
        },
        update = { it.text = text }
    )
}