package com.example.myapplication

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onThemeChanged: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    // Local state for notification toggle just for UI demo
    var notificationsEnabled by remember { mutableStateOf(true) }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        val activity = context as? Activity
        // Find the global header in MainActivity and turn it ON
        val header = activity?.findViewById<View>(R.id.globalHeader)
        val title = activity?.findViewById<TextView>(R.id.globalTitleText)

        header?.visibility = View.VISIBLE
        title?.text = "Settings"
    }

    Scaffold(
        containerColor = if (isDarkTheme) Color(0xFF121212) else Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Section 1: Parking Specifics
            SettingsSectionTitle("Parking & Vehicles", isDarkTheme)
            SettingsCard(isDarkTheme) {
                SettingsItem(
                    title = "My Vehicles",
                    subtitle = "Manage license plates",
                    icon = Icons.Default.DirectionsCar,
                    isDarkTheme = isDarkTheme
                ) {}
                HorizontalDivider()
                SettingsItem(
                    title = "Payment Methods",
                    subtitle = "Visa ending in 4242",
                    icon = Icons.Default.CreditCard,
                    isDarkTheme = isDarkTheme
                ) {}
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 2: App Preferences
            SettingsSectionTitle("Preferences", isDarkTheme)
            SettingsCard(isDarkTheme) {
                // Dark Mode Switch
                SettingsSwitchItem(
                    title = "Dark Mode",
                    icon = Icons.Outlined.DarkMode,
                    checked = isDarkTheme,
                    onCheckedChange = onThemeChanged,
                    isDarkTheme = isDarkTheme
                )
                HorizontalDivider()
                // Notifications Switch
                SettingsSwitchItem(
                    title = "Booking Alerts",
                    subtitle = "Remind me 10 mins before expiry",
                    icon = Icons.Outlined.Notifications,
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    isDarkTheme = isDarkTheme
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Section 3: Support
            SettingsSectionTitle("Support", isDarkTheme)
            SettingsCard(isDarkTheme) {
                SettingsItem(
                    title = "Help Center",
                    icon = Icons.AutoMirrored.Filled.HelpOutline,
                    isDarkTheme = isDarkTheme
                ) {}
                HorizontalDivider()
                SettingsItem(
                    title = "Report an Issue",
                    icon = Icons.Default.BugReport,
                    isDarkTheme = isDarkTheme
                ) {}
            }
        }
    }
}

// --- Helper Components ---

@Composable
fun SettingsSectionTitle(title: String, isDarkTheme: Boolean) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = if (isDarkTheme) Color.Gray else Color.DarkGray,
        modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
    )
}

@Composable
fun SettingsCard(
    isDarkTheme: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isDarkTheme) Color(0xFF1E1E1E) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(content = content)
    }
}

@Composable
fun SettingsItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    isDarkTheme: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (isDarkTheme) Color.White else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, color = textColor)
            if (subtitle != null) {
                Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go",
            tint = Color.LightGray
        )
    }
}

@Composable
fun SettingsSwitchItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isDarkTheme: Boolean
) {
    val textColor = if (isDarkTheme) Color.White else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 16.sp, color = textColor)
            if (subtitle != null) {
                Text(text = subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color.Black // Optional: Customize switch colors
            )
        )
    }
}

@Preview
@Composable
fun SettingsPreview() {
    var isDark by remember { mutableStateOf(false) }
    SettingsScreen(
        onBackClick = {},
        isDarkTheme = isDark,
        onThemeChanged = { isDark = it }
    )
}