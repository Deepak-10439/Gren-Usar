package com.example.gren_usar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gren_usar.data.DataSource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemsScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = { ItemTopBar() },
        bottomBar = { BottomAppBar(navController ) }){
        val grenUiState by mainViewModel.uiState.collectAsState()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) 
        {   
            item { 
                Spacer(modifier = Modifier.padding(65.dp))
            }
            item {
                Spacer(modifier = Modifier.padding(65.dp))
            }
            items(DataSource.loadItems(grenUiState.selectedCategory)) {
                Spacer(modifier = Modifier.height(10.dp))
                ItemCard(
                    stringResourceId = it.stringResourceId,
                    imageResourceId = it.imageResourceId,
                    price = it.Price,
                    value = it.footPrint,
                    onClick = {}
                )
            }
        }
    }

}

@Composable
fun ItemCard(
    stringResourceId: Int,
    imageResourceId: Int,
    price: Int,
    value: Float,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0x1A000000),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(1.dp)
                .width(150.dp)
                .height(200.dp)
                .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
                .clickable { onClick() }
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(imageResourceId),
                        contentDescription = "Item Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = stringResourceId),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "$$price",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF33907C)
                            )
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.carbon_foot_print),
                            contentDescription = null,
                            modifier = Modifier
                                .height(27.dp)
                                .width(30.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        CarbonFootprintBar(value = value)
                    }
                }
            }
        }
    }
}

@Composable
fun CarbonFootprintBar(value: Float) {
    val barColor = if (value > 0.5f) Color(0xFF820000) else Color.Green
    val barWidth = value.coerceIn(0f, 1f) * 110.dp // Assuming 110.dp as the max width for the bar

    Box(
        modifier = Modifier
            .background(Color.Gray) // Background color for the whole bar
            .width(110.dp) // Fixed width to fit the parent container
            .height(12.dp)
    ) {
        Box(
            modifier = Modifier
                .background(barColor)
                .width(barWidth)
                .height(12.dp)
        )
    }
}
@Preview
@Composable
fun ItemTopBar(
navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = GrenScreen.valueOf(
        backStackEntry?.destination?.route ?: GrenScreen.Items.name
    )
    val canNavigateBack = navController.previousBackStackEntry != null
    // For Green Background
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(145.dp)
            .background(color = Color(0xFF33907C))
            .padding(15.dp)
    ) {
        // For Screen name and 3 UI elements
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            // For back Icon and screen name
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(1.dp)
                )
                Text(
                    text = "$currentScreen",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(24.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // For 3 UI elements
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // For Sort By
                Box(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(23.dp)
                        )
                        .width(112.dp)
                        .height(36.dp)
                        .padding(0.dp)
                ) {
                    // For Sort By Text and its icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(7.dp)
                        )
                        Text(
                            text = "Sort By",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier.padding(start = 0.dp)
                        )
                    }
                }

                // For Location
                Box(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(23.dp)
                        )
                        .width(112.dp)
                        .height(36.dp)
                ) {
                    // For Location Text and its icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(7.dp)
                        )
                        Text(
                            text = "Location",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier.padding(start = 0.dp)
                        )
                    }
                }

                // For Category
                Box(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(23.dp)
                        )
                        .width(112.dp)
                        .height(36.dp)
                ) {
                    // For category Text and its icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "Category",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier.padding(start = 0.dp)
                        )
                    }
                }
            }
        }
    }
}
