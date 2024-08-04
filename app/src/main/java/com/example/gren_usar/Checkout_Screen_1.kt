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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckOutScreen_1(
    navController: NavController
) {
    Scaffold(
        topBar = { TopApp_Checkout_1() },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Existing content above the Add Garbage Collection section
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
                            Row {
                                Text(
                                    text = "$25",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF33907C),
                                    )
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "$50",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF4F4F4F),
                                        textDecoration = TextDecoration.LineThrough,
                                    )
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "50% off ",
                                    style = TextStyle(
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF4F4F4F),
                                    )
                                )
                            }
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
                    Column(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Text(
                            text = "Remove",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF4F4F4F),
                            ),
                            modifier = Modifier
                                .alpha(0.5f)
                                .width(59.dp)
                                .height(17.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            modifier = Modifier
                                .width(59.dp)
                                .height(17.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(color = Color(0xFFF6F9FF))
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Exchange Your Garbage For Discount \nand FREE DELIVERY",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF4F4F4F),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .padding(start = 25.dp)
                                .alpha(0.5f)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                .width(142.dp)
                                .height(177.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    modifier = Modifier.size(40.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Add Garbage Collection",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Content at the bottom
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF))
                        .padding(horizontal = 15.dp)
                ) {
                    Column {
                        Text(
                            text = "Price Details",
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Price ( 1 item)\nDelivery Fee",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                ),
                            )
                            Text(
                                text = "$25\n$1",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Right,
                                )
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Total Amount",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF000000),
                                ),
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = "$26",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                ),
                                modifier = Modifier
                                    .padding(top = 5.dp)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFFFFFFFF))
                        .padding(bottom = 20.dp, top = 20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(290.dp)
                            .height(48.dp)
                            .background(
                                color = Color(0xFF33907C),
                                shape = RoundedCornerShape(35.dp)
                            )
                    ) {
                        Text(
                            text = "Continue",
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
                                .clickable {
                                    navController.navigate(GrenScreen.Check_out_Screen_2.name)
                                }
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun TopApp_Checkout_1(
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
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 15.dp) // Padding for icon
                    .size(24.dp)
                    .clickable {
                        navController.navigate(GrenScreen.ProductDetail.name)
                    }
            )
            Spacer(modifier = Modifier.width(5.dp)) // Spacing between icon and text
            Box(
                modifier = Modifier
                    .weight(1f) // Ensure text is centered
                    .fillMaxWidth()
            ) {
                Text(
                    text = "My Cart",
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

@Composable
fun TopApp_Checkout_2() {
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
                .padding(top = 25.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, bottom = 30.dp)
                    .size(24.dp)
                    .clickable {

                    }
            )
            Spacer(modifier = Modifier.width(5.dp)) // Spacing between icon and text
            Box(
                modifier = Modifier
                    .padding(bottom = 0.dp)
                    .weight(1f) // Ensure text is centered
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Take a Photo of Garbage",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat)),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .align(Alignment.Center) // Center the text
                        .fillMaxHeight()
                )
            }
        }
    }
}

