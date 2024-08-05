package com.example.gren_usar

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckOutSuccess(
    navController: NavController
) {
    Scaffold(
        topBar = {TopApp_Checkout_Success()}
    ) {innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 100.dp, top = 30.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.done),
                        contentDescription = null,
                        modifier = Modifier
                            .width(160.31682.dp)
                            .height(100.99998.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "Thanks for Order",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF4F4F4F),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .background(color = Color(0xFFFFFFFF))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 35.dp, end = 12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.eco_toilet_paper_2),
                            contentDescription = "image description",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(122.dp)
                                .height(122.dp)
                                .background(
                                    color = Color(0xFFC4C4C4),
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                .align(Alignment.CenterVertically)
                        )
                        Column {
                            Text(
                                text = "ECO Toiler Paper",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF4F4F4F),
                                )
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                    text = "$25",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF33907C),
                                    )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Qty : 1",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF4F4F4F),
                                )
                            )
                        }
                    }
            }
        }

            item {
                Spacer(modifier = Modifier.height(15.dp))
            }
            item {
                Box(modifier = Modifier
                    .shadow(
                        elevation = 28.dp,
                        spotColor = Color(0x12000000),
                        ambientColor = Color(0x12000000)
                    )
                    .padding(0.dp)
                    .width(379.dp)
                    .height(404.dp)
                    .background(color = Color(0xFFFFFFFF)))
                {
                    Column {
                        Text(
                            text = "Track Order",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF212121),
                            ),
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                        Text(
                            text = "Order ID - 123455",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF606A7B),
                            ),
                            modifier = Modifier
                                .padding(start = 20.dp, top = 5.dp)
                        )
                        
                        Box(
                            modifier = Modifier
                                .padding(start = 20.dp, top = 15.dp)
                                .border(width = 3.dp, color = Color(0xFF33907C))
                                .padding(3.dp)
                                .width(68.dp)
                                .height(1.dp)
                        ){

                        }
                        
                        Image(
                            painter = painterResource(id = R.drawable.screenshot_2024_08_05_132444),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .align(Alignment.End)
                                .padding(bottom = 15.dp, start = 15.dp)
                            )

                    }
                }
            }

            item {
                Box(modifier = Modifier
                    .padding(0.dp)
                    .width(379.dp)
                    .height(146.dp)){
                    Image(painter = painterResource(id = R.drawable.delivery_address), contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }
            }
            item{
                Spacer(modifier = Modifier.height(20.dp))
            }
            item{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color(0xFFF6F9FF))
                        .clickable {
                            navController.navigate(GrenScreen.Home.name)
                        }

                ){
                    Text(
                        text = "Continue Shopping",
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF212121),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
    }
}

@Composable
fun TopApp_Checkout_Success(
    navController: NavController = NavController(LocalContext.current)
) {
    Box(
        modifier = Modifier
            .padding(top = 45.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFF33907C))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 15.dp) // Padding for icon
                    .size(24.dp)
                    .clickable {
                        navController.navigate(GrenScreen.Home.name)
                    }
            )
            Spacer(modifier = Modifier.width(5.dp)) // Spacing between icon and text
            Box(
                modifier = Modifier
                    .weight(1f) // Ensure text is centered
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Order Details",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.Center) // Center the text
                        .fillMaxHeight()
                )
            }
        }
    }
}
}

@Composable
fun TopApp_Checkout_Success(
    navController: NavController = NavController(LocalContext.current)
) {
    Box(
        modifier = Modifier
            .padding(top = 45.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFF33907C))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 15.dp) // Padding for icon
                    .size(24.dp)
                    .clickable {
                        navController.navigate(GrenScreen.Home.name)
                    }
            )
            Spacer(modifier = Modifier.width(5.dp)) // Spacing between icon and text
            Box(
                modifier = Modifier
                    .weight(1f) // Ensure text is centered
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Order Details",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.Center) // Center the text
                        .fillMaxHeight()
                )
            }
        }
    }
}

