package com.example.gren_usar

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gren_usar.data.DataSource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    onCategoryClick: (Int) -> Unit
) {
    Scaffold(
        bottomBar = { BottomAppBar(navController = navController) }
    ) {
        var banner_image = Banner_Images()
        Column {
            banner_image
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(16.dp)
            ) {
                items(DataSource.loadCategories()) { category ->
                    CategoryCard(
                        context = LocalContext.current,
                        stringResourceId = category.stringResourceId,
                        imageResourceId = category.imageResourceId,
                        mainViewModel = mainViewModel,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    context: Context,
    stringResourceId: Int,
    imageResourceId: Int,
    mainViewModel: MainViewModel,
    onCategoryClick: (Int) -> Unit
) {
    val categoryName = stringResource(id = stringResourceId)
    Card(
        modifier = Modifier
            .padding(1.dp)
            .background(color = Color.White)
            .clickable {
                mainViewModel.updateClickStatus(categoryName)
                Toast
                    .makeText(
                        context,
                        "Selected Category: $categoryName was clicked",
                        Toast.LENGTH_SHORT
                    )
                    .show()
                onCategoryClick(stringResourceId)
            },
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFF000000))
                .width(93.dp)
                .height(93.dp)
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .width(93.dp)
                    .height(93.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = categoryName,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight(600),
                    color = Color.White
                ),
                modifier = Modifier
                    .height(60.dp)
                    .padding(4.dp)
                    .align(Alignment.BottomCenter),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun Banner_Images() {
    Card(
        modifier = Modifier
            .background(color = Color.White)
            .clickable {}
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.earth_banner),
                contentDescription = null,
                modifier = Modifier
                    .shadow(elevation = 43.dp)
                    .padding(14.dp)
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column {
                Image(
                    painter = painterResource(id = R.drawable.fashionable_hats),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun BottomAppBar(navController: NavController) {
    var selectedScreen by remember { mutableStateOf(GrenScreen.Home.name) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
    ) {

        //For Home Icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                selectedScreen = GrenScreen.Home.name
                navController.navigate(GrenScreen.Home.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val iconColor = if (selectedScreen == GrenScreen.Home.name) Color(0xFF33907C) else Color.Gray
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = iconColor
            )
            Text(
                text = "Home",
                fontSize = 10.sp,
                color = iconColor
            )
        }

        //For Browse Icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                selectedScreen = GrenScreen.Browse.name
                navController.navigate(GrenScreen.Browse.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val iconColor = if (selectedScreen == GrenScreen.Browse.name) Color(0xFF33907C) else Color.Gray
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Browse",
                tint = iconColor
            )
            Text(
                text = "Browse",
                fontSize = 10.sp,
                color = iconColor
            )
        }

        //For Store Icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                selectedScreen = GrenScreen.Store.name
                navController.navigate(GrenScreen.Store.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val iconColor = if (selectedScreen == GrenScreen.Store.name) Color(0xFF33907C) else Color.Gray
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Store",
                tint = iconColor
            )
            Text(
                text = "Store",
                fontSize = 10.sp,
                color = iconColor
            )
        }

        //For Order History Icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                selectedScreen = GrenScreen.OrderHistory.name
                navController.navigate(GrenScreen.OrderHistory.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val iconColor = if (selectedScreen == GrenScreen.OrderHistory.name) Color(0xFF33907C) else Color.Gray
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Order History",
                tint = iconColor
            )
            Text(
                text = "Order History",
                fontSize = 10.sp,
                color = iconColor
            )
        }

        //For Profile Icon
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.clickable {
                selectedScreen = GrenScreen.Profile.name
                navController.navigate(GrenScreen.Profile.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            val iconColor = if (selectedScreen == GrenScreen.Profile.name) Color(0xFF33907C) else Color.Gray
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = iconColor
            )
            Text(
                text = "Profile",
                fontSize = 10.sp,
                color = iconColor
            )
        }
    }
}
