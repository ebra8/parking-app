package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            com.example.myapplication.ui.theme.MyApplicationTheme {
                // Get the current state from our helper
                val context = androidx.compose.ui.platform.LocalContext.current
                var isDarkTheme by remember { mutableStateOf(ThemeUtils.isDarkTheme(context)) }

                SettingsScreen(
                    onBackClick = { finish() },
                    isDarkTheme = isDarkTheme,
                    onThemeChanged = { newMode ->
                        // Update UI immediately
                        isDarkTheme = newMode

                        // Save to storage
                        ThemeUtils.saveTheme(context, newMode)

                        // Apply to the whole app (XML & Compose)
                        ThemeUtils.applyTheme(newMode)
                    }
                )
            }
        }
    }
}