package com.example.gren_usar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController

@Composable
fun ProductDetail(
    navController: NavController
) {
    var showEcoImpact by remember { mutableStateOf(true) }

    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
        // For top image and its icons
        item {
            // For top image shape rectangle and its components
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(226.dp)
                    .background(color = Color.Black)
            ) {
                // Top image
                Image(
                    painter = painterResource(id = R.drawable.ultra_soft_toilet_paper),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                // For icons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // For back icon
                    Box(
                        modifier = Modifier
                            .alpha(0.5f)
                            .size(32.dp)
                            .background(
                                color = Color.White, shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }

                    // For share, like and 3 dots icon
                    Row {

                        Box(
                            modifier = Modifier
                                .alpha(0.5f)
                                .size(32.dp)
                                .background(
                                    color = Color.White, shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                        // For like icon
                        Box(
                            modifier = Modifier
                                .alpha(0.5f)
                                .padding(start = 8.dp)
                                .size(32.dp)
                                .background(
                                    color = Color.White, shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            LikeIcon()
                        }

                        // For 3 dots
                        Box(
                            modifier = Modifier
                                .alpha(0.5f)
                                .padding(start = 8.dp)
                                .size(32.dp)
                                .background(
                                    color = Color.White, shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        }

        // For Product name its carbon footprint, price and scale
        item {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 43.dp,
                        spotColor = Color(0x123A4C82),
                        ambientColor = Color(0x123A4C82)
                    )
                    .padding(0.dp)
                    .width(375.dp)
                    .height(95.dp)
                    .background(color = Color(0xFFFFFFFF))
            ) {
                Column {
                    // For product name and its price
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp, end = 12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "ULTRA SOFT TOILET PAPER",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF4F4F4F),
                            )
                        )
                        Text(
                            text = "$25",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF33907C),
                            )
                        )
                    }
                    // For carbon footprint and its scale
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp, top = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.carbon_foot_print),
                            contentDescription = null,
                            modifier = Modifier
                                .width(46.dp)
                                .height(40.dp)
                        )
                        Spacer(modifier = Modifier.height(9.dp))
                        CarbonFootprintBarProductDetail(value = 0.75f)
                        Text(
                            text = "750gm",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFF7272),
                            ),
                            modifier = Modifier.padding(start = 53.dp)
                        )
                    }
                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 43.dp,
                        spotColor = Color(0x123A4C82),
                        ambientColor = Color(0x123A4C82)
                    )
                    .padding(0.dp)
                    .fillMaxWidth()
                    .height(72.dp)
                    .background(
                        color = Color(0xFFEFEAEA), shape = RoundedCornerShape(size = 11.dp)
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .shadow(
                            elevation = 43.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .padding(0.dp)
                        .width(200.dp)
                        .height(72.dp)
                        .background(
                            color = Color(0xFFEFEAEA),
                            shape = RoundedCornerShape(size = 11.dp)
                        )
                        .clickable { showEcoImpact = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ECO IMPACT",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF4F4F4F),
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 40.dp, end = 30.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .shadow(
                            elevation = 43.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .padding(0.dp)
                        .width(191.dp)
                        .height(72.dp)
                        .align(Alignment.BottomEnd)
                        .background(color = Color(0xFFCFFFDC))
                        .clickable { showEcoImpact = false },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Alternatives",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF4F4F4F),
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 40.dp, end = 30.dp)
                    )
                }
            }
        }
        item {
            if (showEcoImpact) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp) // Adjust as needed
                        .background(
                            color = Color(0xFFEFEAEA)
                        )
                ) {
                    // Eco Impact screen content
                    Text(
                        text = "This is equivalent to",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF33907C),
                        ),
                        modifier = Modifier
                            .padding(top = 35.dp, start = 93.dp, end = 36.dp)
                            .width(206.dp)
                            .height(22.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.bottle),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 35.dp)
                            .width(120.dp)
                    )
                    Text(
                        text = "19 Plastic Bottles",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(900),
                            color = Color(0xFF4F4F4F),
                            ),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(end = 80.dp, top = 0.dp, bottom = 70.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.car),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 40.dp, start = 60.dp)
                    )
                    Text(
                        text = "7 kms car travel",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF4F4F4F),
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 20.dp, end = 30.dp)
                    )
                    Text(
                        text = "The more you know:",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF33907C),
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 30.dp, bottom = 65.dp)
                    )
                    Text(
                        text = "One Mega Ultra Soft Toilet paper emits more than 750 gm CO2 from Tree to your Toilet ",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF4F4F4F),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 30.dp, bottom = 35.dp)
                            .width(298.dp)
                            .height(28.dp)
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp) // Adjust as needed
                        .background(
                            color = Color(0xFFCFFFDC)
                        ),
                ) {
                    // Alternatives screen content
                    Row {
                        Column(
                            modifier = Modifier
                                .padding(start = 20.dp,top = 10.dp)
                        ) {
                            ItemCard(
                                    stringResourceId = R.string.Alternative1,
                                    imageResourceId = R.drawable.eco_toilet_paper,
                                    price = 25,
                                    value = 0.25f,
                                    onClick = {

                                    }
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(130.dp)
                                    .height(50.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp)
                                    )
                            ){
                                Text(
                                    text = "BUY NOW",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF4F4F4F),
                                        textAlign = TextAlign.Center,
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.Center)

                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 10.dp)
                        ) {
                            ItemCard(
                                stringResourceId = R.string.Alternative2,
                                imageResourceId = R.drawable.eco_toilet_paper_2,
                                price = 25,
                                value = 0.25f,
                                onClick = {
                                    navController.navigate(GrenScreen.Check_out_Screen_1.name)
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(130.dp)
                                    .height(50.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp)
                                    )
                            ){
                                Text(
                                    text = "BUY NOW",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF4F4F4F),
                                        textAlign = TextAlign.Center,
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Box(modifier = Modifier
                .shadow(
                    elevation = 38.dp,
                    spotColor = Color(0x123A4C82),
                    ambientColor = Color(0x123A4C82)
                )
                .padding(0.dp)
                .fillMaxWidth()
                .height(70.dp)
                .background(color = Color(0xFFFFFFFF))
            ){
                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .width(311.dp)
                    .height(48.dp)
                    .background(color = Color(0xFF33907C), shape = RoundedCornerShape(15.dp))
                    .clickable {
                        navController.navigate(GrenScreen.Check_out_Screen_1.name)
                    }
                ){
                    Text(
                        text = "Add To Cart",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 18.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ){
                Column {
                    Text(
                        text = "Details",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .padding(start = 30.dp, top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row()
                    {
                        Text(
                            text = "Condition\nPrice Type\nCategory\nLocation",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4F4F4F),
                            ),
                            modifier = Modifier.padding(start = 30.dp, top = 10.dp)
                        )
                        Text(
                            text = "Organic\nFixed\nBeverages\nKualalumpur, Malaysia",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4F4F4F),
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 100.dp, top = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Additional Details",
                        style = TextStyle(
                            fontSize = 18.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .padding(start = 30.dp, top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = "Delivery Details",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF4F4F4F),
                            ),
                            modifier = Modifier
                                .padding(start = 30.dp, top = 10.dp)
                                .alpha(0.7f)
                                .width(112.dp)
                                .height(121.dp)
                        )
                        Text(
                            text = "Home Delivery Available, Cash On Delivery",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF4F4F4F),
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 100.dp, top = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CarbonFootprintBarProductDetail(value: Float) {
    val barColor = if (value > 0.5f) Color(0xFF820000) else Color.Green
    val barWidth = value.coerceIn(0f, 1f) * 200.dp // Assuming 218.dp as the max width for the bar

    Box(
        modifier = Modifier
            .background(Color.Gray) // Background color for the whole bar
            .width(200.dp) // Fixed width to fit the parent container
            .height(12.dp),

    ) {
        Box(
            modifier = Modifier
                .background(barColor)
                .width(barWidth)
                .height(12.dp)
        )
    }
}
@Composable
fun LikeIcon() {
    var isLiked by remember { mutableStateOf(false) }

    val icon: ImageVector = if (isLiked) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }

    val tint = if (isLiked) {
        Color.Red
    } else {
        Color.Gray
    }

    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = tint,
        modifier = Modifier
            .clickable { isLiked = !isLiked }
            .size(24.dp)
    )
}


