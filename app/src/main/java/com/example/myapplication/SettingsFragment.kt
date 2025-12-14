package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import com.example.myapplication.ui.theme.MyApplicationTheme

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Use ComposeView for Fragments instead of setContent
        return ComposeView(requireContext()).apply {
            setContent {
                MyApplicationTheme {
                    val context = LocalContext.current

                    // 1. Get initial state from ThemeUtils
                    var isDarkTheme by remember {
                        mutableStateOf(ThemeUtils.isDarkTheme(context))
                    }

                    SettingsScreen(
                        // 2. Navigation: Pop back stack instead of finish()
                        onBackClick = { parentFragmentManager.popBackStack() },
                        isDarkTheme = isDarkTheme,
                        onThemeChanged = { newMode ->
                            // Save to specific preference file
                            ThemeUtils.saveTheme(context, newMode)
                            // Apply theme to the entire app instantly
                            ThemeUtils.applyTheme(newMode)
                        }
                    )
                }
            }
        }
    }
}