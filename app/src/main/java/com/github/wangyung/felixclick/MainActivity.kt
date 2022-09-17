package com.github.wangyung.felixclick

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.wangyung.felixclick.ui.screen.LockScreen
import com.github.wangyung.felixclick.ui.screen.MainScreen
import com.github.wangyung.felixclick.ui.theme.FelixClickTheme
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

private const val KEY_LAST_ACCESS = "last_access"

class MainActivity : ComponentActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        setContent {
            val lastLaunchTime = sharedPreferences.getLong(KEY_LAST_ACCESS, 0)
            var launchTime = System.currentTimeMillis()
            val delta = launchTime - lastLaunchTime
            val isLock = lastLaunchTime != 0L &&
                    delta > TimeUnit.MINUTES.toMillis(10) &&
                    delta < TimeUnit.HOURS.toMillis(12)
            FelixClickTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val shouldLockScreen = remember {
                        mutableStateOf(isLock)
                    }
                    if (shouldLockScreen.value) {
                        LockScreen(modifier = Modifier.fillMaxSize(), shouldLockScreen)
                    } else {
                        launchTime = System.currentTimeMillis()
                        updateLauchTime(launchTime)
                        MainScreen(modifier = Modifier.fillMaxSize(), launchedTime = launchTime)
                    }
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(1000)
                            if (!shouldLockScreen.value) {
                                val now = System.currentTimeMillis()
                                Log.d("TEST", "now: $now, launchTime: $launchTime = ${now - launchTime}")
                                if (now - launchTime > TimeUnit.MINUTES.toMillis(10)) {
                                    shouldLockScreen.value = true
                                }
                            }
                        }
                    }
                }
            }
        }

        updateLauchTime(System.currentTimeMillis())
    }

    private fun updateLauchTime(timestamp: Long) =
        with (sharedPreferences.edit()) {
            putLong(KEY_LAST_ACCESS, timestamp)
            apply()
        }
}
