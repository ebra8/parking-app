package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDark = ThemeUtils.isDarkTheme(this)
        ThemeUtils.applyTheme(isDark)

        val user = FirebaseAuth.getInstance().currentUser
        val displayName = user?.displayName ?: user?.email ?: "Guest"

        setContent {
            com.example.myapplication.ui.theme.MyApplicationTheme {

                ProfileScreen(
                    username = displayName,
                    onBackClick = { finish() },
                    onLogoutClick = {
                        FirebaseAuth.getInstance().signOut()
                        finish()
                    },
                    onSettingsClick = {
                        val intent = android.content.Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                    }
                )

            }
        }
    }
}