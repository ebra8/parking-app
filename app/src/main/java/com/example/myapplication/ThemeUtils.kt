package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_IS_DARK = "is_dark_mode"

    // 1. Save the choice to memory
    fun saveTheme(context: Context, isDark: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_IS_DARK, isDark).apply()
    }

    // 2. Read the saved choice
    fun isDarkTheme(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_DARK, false) // Default to Light (false)
    }

    // 3. Force the app to switch modes
    fun applyTheme(isDark: Boolean) {
        val mode = if (isDark) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}