package com.example.gren_usar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomAppBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val currentScreen = currentDestination?.route ?: GrenScreen.Home.name

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
//            .shadow(elevation = 18.dp)
            .navigationBarsPadding()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 0.dp)
        ) {
            BottomAppBarItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = currentScreen == GrenScreen.Home.name,
                onClick = { navigateToScreen(navController, GrenScreen.Home.name) }
            )
            BottomAppBarItem(
                icon = Icons.Default.Search,
                label = "Browse",
                isSelected = currentScreen == GrenScreen.Browse.name,
                onClick = { navigateToScreen(navController, GrenScreen.Browse.name) }
            )
            BottomAppBarItem(
                icon = Icons.Default.ShoppingCart,
                label = "Store",
                isSelected = currentScreen == GrenScreen.Store.name,
                onClick = { navigateToScreen(navController, GrenScreen.Store.name) }
            )
            BottomAppBarItem(
                icon = Icons.Default.Email,
                label = "Orders",
                isSelected = currentScreen == GrenScreen.OrderHistory.name,
                onClick = { navigateToScreen(navController, GrenScreen.OrderHistory.name) }
            )
            BottomAppBarItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = currentScreen == GrenScreen.Profile.name,
                onClick = { navigateToScreen(navController, GrenScreen.Profile.name) }
            )
        }
    }
}

@Composable
fun BottomAppBarItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val iconColor = if (isSelected) Color(0xFF33907C) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = iconColor,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
