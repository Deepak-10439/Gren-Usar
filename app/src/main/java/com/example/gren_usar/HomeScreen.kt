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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
        modifier = Modifier.navigationBarsPadding(),
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
                Column(modifier = Modifier.height(187.dp)) {
                    val categories = DataSource.loadCategories()
                    for (i in 0 until 2) {
                        Row {
                            for (j in 0 until 4) {
                                val index = i * 4 + j
                                if (index < categories.size) {
                                    CategoryCard(
                                        context = LocalContext.current,
                                        stringResourceId = categories[index].stringResourceId,
                                        imageResourceId = categories[index].imageResourceId,
                                        mainViewModel = mainViewModel,
                                        onCategoryClick = onCategoryClick,
                                        modifier = Modifier.weight(1f)
                                    )
                                } else {
                                    Spacer(modifier = Modifier.weight(2f))
                                }
                            }
                        }
                    }
                }
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
                        ItemCardHome(
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
                        ItemCardHome(
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
fun ItemCardHome(
    stringResourceId: Int,
    imageResourceId: Int,
    price: Int,
    value: Float,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(220.dp)
            .padding(end = 16.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = stringResourceId),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$$price",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF33907C)
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.carbon_foot_print),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    CarbonFootprintBarHome(value = value, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
@Composable
fun CarbonFootprintBarHome(value: Float, modifier: Modifier = Modifier) {
    val barColor = when {
        value <= 0.3f -> Color(0xFF4CAF50)
        value <= 0.7f -> Color(0xFFFFC107)
        else -> Color(0xFFF44336)
    }
    val barWidth = value.coerceIn(0f, 1f) * 100

    Box(
        modifier = modifier
            .height(4.dp)
            .background(Color(0xFFE0E0E0), RoundedCornerShape(2.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(barWidth.dp)
                .background(barColor, RoundedCornerShape(2.dp))
        )
    }
}


@Composable
fun CategoryCard(
    context: Context,
    stringResourceId: Int,
    imageResourceId: Int,
    mainViewModel: MainViewModel,
    onCategoryClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val categoryName = stringResource(id = stringResourceId)
    Card(
        modifier = Modifier
            .padding(4.dp)
            .size(100.dp)
            .clickable {
                mainViewModel.updateClickStatus(categoryName)
                Toast.makeText(
                    context,
                    "Selected Category: $categoryName",
                    Toast.LENGTH_SHORT
                ).show()
                onCategoryClick(stringResourceId)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0x80000000))
                        )
                    )
            )
            Text(
                text = categoryName,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat)),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

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

