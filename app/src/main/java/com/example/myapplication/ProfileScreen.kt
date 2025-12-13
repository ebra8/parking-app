package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



// 1. Data model for the menu options
data class ProfileMenuOption(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun ProfileScreen(
    username: String,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    // Define the menu items
    val menuOptions = listOf(
        ProfileMenuOption("Your profile", Icons.Outlined.Person) {},
//        ProfileMenuOption("Payment Methods", Icons.Outlined.CreditCard) {},
        ProfileMenuOption("Settings", Icons.Outlined.Settings, onSettingsClick),
        ProfileMenuOption("Help Center", Icons.Outlined.Info) {},
        ProfileMenuOption("Privacy Policy", Icons.Outlined.Lock) {},
//        ProfileMenuOption("Invites Friends", Icons.Outlined.PersonAdd) {},
        ProfileMenuOption("Log out", Icons.AutoMirrored.Filled.ExitToApp, onLogoutClick)
    )

    Scaffold(
        topBar = {
            ProfileTopBar(onBackClick)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()), // Makes screen scrollable
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 2. Profile Avatar Section
            ProfileHeaderSection(username = username)

            Spacer(modifier = Modifier.height(24.dp))

            // 3. Menu List Section
            menuOptions.forEach { option ->
                ProfileMenuItem(option)
            }
        }
    }
}

@Composable
fun ProfileTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }
        Text(
            text = "Profile",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ProfileHeaderSection(username: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomEnd) {
            // Avatar Image
            // Replace R.drawable.profile_placeholder with your actual image resource
            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = Color.LightGray // Placeholder color if image fails
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()
                )
            }

            // Edit Icon Badge
            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .offset(x = 4.dp, y = 4.dp), // Slight offset
                shape = CircleShape,
                color = Color.Gray // Dark gray background for edit icon
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profile",
                    tint = Color.White,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileMenuItem(option: ProfileMenuOption) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { option.onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Leading Icon
        Icon(
            imageVector = option.icon,
            contentDescription = option.title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Title
        Text(
            text = option.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f) // Pushes the arrow to the right
        )

        // Trailing Arrow
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        username = "Guest",
        onBackClick = {},
        onLogoutClick = {},
        onSettingsClick = {}
    )
}