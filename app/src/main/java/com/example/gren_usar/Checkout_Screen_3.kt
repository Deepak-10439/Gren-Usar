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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckOutScreen_3(
    navController: NavController
) {
    var labelSize by remember { mutableStateOf(0) }
    var buttonText by remember { mutableStateOf("Continue") }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1000L)
        labelSize = 13
        buttonText = "Place Order"
    }

    Scaffold(
        topBar = { TopApp_Checkout_1(navController) },
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
                                .height(177.dp)
                                .clickable { 
                                    navController.navigate(GrenScreen.Check_out_Screen_2.name)
                                },
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
                                text = "$25\n Free",
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
                        // Add the new Text here based on the labelSize state
                        if (labelSize > 0) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 15.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Garbage x$labelSize",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        lineHeight = 20.sp,
                                        fontFamily = FontFamily(Font(R.font.montserrat)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFF000000),
                                    ),
                                )
                                Text(
                                    text = "-3$",
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
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Total",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF000000),
                                ),
                            )
                            Text(
                                text = "$22",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Right,
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                        Button(
                            onClick = {
                                // Handle the place order logic
                                navController.navigate(GrenScreen.Check_out_Success.name)
                            },
                            modifier = Modifier
                                .width(311.dp)
                                .height(48.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF33907C),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Place Order",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
