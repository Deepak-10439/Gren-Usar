package com.example.gren_usar

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
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
import androidx.navigation.compose.currentBackStackEntryAsState
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
        topBar = { TopAppBar(navController = navController) },
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Display the banner image
            item {
                Banner_Images()
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Display the grid of categories
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    modifier = Modifier
                        .padding(5.dp)
                        .height(187.dp)
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
                Spacer(modifier = Modifier.height(10.dp))
            }

            // New product and see all composable
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(160.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.brands_to_follow),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 25.dp, start = 20.dp)
                            .width(121.71206.dp)
                            .height(20.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(87.dp)
                            .height(23.dp)
                            .background(
                                color = Color(0xFF33907C),
                                shape = RoundedCornerShape(size = 24.dp)
                            )
                    ) {
                        Text(
                            text = "See All",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // New Products displaying
            item {
                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    items(DataSource.loadItems(R.string.New_Product)) { item ->
                        ItemCard(
                            stringResourceId = item.stringResourceId,
                            imageResourceId = item.imageResourceId,
                            price = item.Price,
                            value = item.footPrint,
                            onClick = {
                                navController.navigate(GrenScreen.ProductDetail.name)
                            }
                        )
                    }
                }
            }

            // Popular product and see all composable
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(160.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.popular),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 25.dp, start = 20.dp)
                            .width(121.71206.dp)
                            .height(20.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(87.dp)
                            .height(23.dp)
                            .background(
                                color = Color(0xFF33907C),
                                shape = RoundedCornerShape(size = 24.dp)
                            )
                    ) {
                        Text(
                            text = "See All",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            ),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // Popular Products displaying
            item {
                LazyRow(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                ) {
                    items(DataSource.loadItems(R.string.Popular_Product)) { item ->
                        ItemCard(
                            stringResourceId = item.stringResourceId,
                            imageResourceId = item.imageResourceId,
                            price = item.Price,
                            value = item.footPrint,
                            onClick = {
                                navController.navigate("productDetail")
                            }
                        )
                    }
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
            .fillMaxWidth()
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
    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(160.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.earth_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 43.dp,
                    spotColor = Color(0x123A4C82),
                    ambientColor = Color(0x123A4C82)
                )
                .padding(0.dp)
                .height(165.dp)
                .background(color = Color(0xFF000000))
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fashionable_hats),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 25.dp, top = 20.dp, bottom = 15.dp)
            )
            Box(modifier = Modifier
                .padding(start = 20.dp, top = 10.dp)
                .width(147.dp)
                .height(28.dp)
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
                .background(color = Color.Transparent, shape = RoundedCornerShape(12.dp)))
            {
                Image(
                    painter = painterResource(id = R.drawable.get_promocode_copy),
                    contentDescription =null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun BottomAppBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val currentScreen = currentDestination?.route ?: GrenScreen.Home.name

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(bottom = 35.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        //For Home Icon
        BottomAppBarItem(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = currentScreen == GrenScreen.Home.name,
            onClick = {
                navController.navigate(GrenScreen.Home.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        //For Browse Icon
        BottomAppBarItem(
            icon = Icons.Default.Search,
            label = "Browse",
            isSelected = currentScreen == GrenScreen.Browse.name,
            onClick = {
//                navController.navigate(GrenScreen.Browse.name) {
//                    popUpTo(navController.graph.startDestinationId) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
            }
        )

        //For Store Icon
        BottomAppBarItem(
            icon = Icons.Default.ShoppingCart,
            label = "Store",
            isSelected = currentScreen == GrenScreen.Store.name,
            onClick = {
//                navController.navigate(GrenScreen.Store.name) {
//                    popUpTo(navController.graph.startDestinationId) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
            }
        )

        //For Order History Icon
        BottomAppBarItem(
            icon = Icons.Default.Email,
            label = "Order History",
            isSelected = currentScreen == GrenScreen.OrderHistory.name,
            onClick = {
//                navController.navigate(GrenScreen.OrderHistory.name) {
//                    popUpTo(navController.graph.startDestinationId) {
//                        saveState = true
//                    }
//                    launchSingleTop = true
//                    restoreState = true
//                }
            }
        )

        //For Profile Icon
        // Profile Icon
        BottomAppBarItem(
            icon = Icons.Default.Person,
            label = "Profile",
            isSelected = currentScreen == GrenScreen.Profile.name,
            onClick = {
                navigateToScreen(navController, GrenScreen.Profile.name)
            }
        )
    }
}

fun navigateToScreen(navController: NavController, screen: String) {
    try {
        Log.d("BottomAppBar", "Navigating to $screen")
        navController.navigate(screen) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    } catch (e: Exception) {
        Log.e("BottomAppBar", "Navigation error: ${e.message}")
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
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = iconColor
        )
        Text(
            text = label,
            fontSize = 10.sp,
            color = iconColor
        )
    }
}

@Composable
fun TopAppBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color(0xFF33907C))
            .padding(top = 10.dp)
    ) {
        // For categories and search bar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // For Categories Text, carbon footprint icon, and shopping cart icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Categories",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF)
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.carbon_foot_print_white),
                        contentDescription = null,
                        modifier = Modifier
                            .width(56.dp)
                            .height(62.dp)
                            .padding(horizontal = 10.dp)
                            .clickable {
                                Log.d("TopAppBar", "Carbon footprint button clicked")
                                try {
                                    navController.navigate(GrenScreen.Profile.name) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                } catch (e: Exception) {
                                    Log.e("TopAppBar", "Navigation error: ${e.message}")
                                }
                            }
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color.White // Set the tint color to white
                    )
                }
            }

            // For search bar, its icon, its shape, and text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(23.dp))
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Search",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


//@Composable
//fun ProfileScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Profile Screen", style = MaterialTheme.typography.displayLarge)
//    }
//}