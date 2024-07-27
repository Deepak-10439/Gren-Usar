package com.example.gren_usar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Preview
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Green Background
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(402.dp)
                .background(color = Color(0xFF33907C))
        ) {
            // Profile text and icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Profile",
                    style = TextStyle(
                        fontSize = 30.sp,
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
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Profile Icon and user information
            Row(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar2),
                    contentDescription = null,
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .padding(1.dp)
                        .width(64.dp)
                        .height(64.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Team Area51",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "8178663980",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.alpha(0.8f)
                    )
                    Text(
                        text = "info@Area51",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.montserrat)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.alpha(0.8f)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 10.dp)
                            .shadow(
                                elevation = 4.dp,
                                spotColor = Color(0x40000000),
                                ambientColor = Color(0x40000000)
                            )
                            .border(
                                width = 3.dp,
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(size = 14.dp)
                            )
                            .width(160.dp)
                            .height(75.dp)
                            .background(
                                color = Color(0x2B4F4F4F),
                                shape = RoundedCornerShape(size = 14.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Total CO2 Footprint",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "2700 gm",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                ),
                                modifier = Modifier.padding(start = 22.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(top = 0.dp, start = 19.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(21.dp)
                                .height(21.dp),
                            tint = Color(0xFFFCDE76)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(21.dp)
                                .height(21.dp),
                            tint = Color(0xFFFCDE76)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(21.dp)
                                .height(21.dp),
                            tint = Color(0xFFFCDE76)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(21.dp)
                                .height(21.dp),
                            tint = Color(0xFFFCDE76)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .width(21.dp)
                                .height(21.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }

        // White Background
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 402.dp)
                .background(color = Color.White)
        ) {
            // Additional white background content here
            Spacer(modifier = Modifier.height(200.dp))
            Row {
                Image(
                    painter = painterResource(id = R.drawable.tree),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Column {
                    Box(modifier = Modifier
                        .shadow(
                            elevation = 43.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .padding(top = 40.dp)
                        .width(168.dp)
                        .height(57.dp)
                        .background(
                            color = Color(0x30FF7272),
                            shape = RoundedCornerShape(size = 10.dp)
                        ))
                    {
                        Column {
                            Text(
                                text = "Total Red Products",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF4F4F4F),
                                ),
                                modifier = Modifier.padding(start = 13.dp, top = 5.dp)
                            )
                            Text(
                                text = "06",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF4F4F4F),
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 5.dp)
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .shadow(
                            elevation = 43.dp,
                            spotColor = Color(0x123A4C82),
                            ambientColor = Color(0x123A4C82)
                        )
                        .padding(top = 10.dp)
                        .width(168.dp)
                        .height(57.dp)
                        .background(
                            color = Color(0x4D33CCAB),
                            shape = RoundedCornerShape(size = 10.dp)
                        ))
                    {
                        Column {
                            Text(
                                text = "Total Gren Products",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF4F4F4F),
                                ),
                                modifier = Modifier.padding(start = 13.dp, top = 5.dp)
                            )
                            Text(
                                text = "21",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.montserrat)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF4F4F4F),
                                ),
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 5.dp)
                            )
                        }
                    }
                }
            }
        }

        Row {
            RectangleBarGraph(
                height = 260,
                month = "Aug",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
            RectangleBarGraph(
                height = 167,
                month = "Sep",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 30.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
            RectangleBarGraph(
                height = 217,
                month = "Oct",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 30.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
            RectangleBarGraph(
                height = 156,
                month = "Nov",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 30.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
            RectangleBarGraph(
                height = 143,
                month = "Dec",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 30.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
            RectangleBarGraph(
                height = 94,
                month = "Jan",
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 30.dp)
                    .offset(y = 255.dp) // Adjust this value to move the graph up or down
            )
        }
        // RectangleBarGraph in the center overlapping both backgrounds

    }
}

@Composable
fun RectangleBarGraph(height: Int, month: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Box(
            Modifier
                .width(30.dp)
                .height(height.dp)
                .background(
                    color = Color(0xFF94E380),
                    shape = RoundedCornerShape(
                        topStart = 25.dp,
                        topEnd = 25.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = month,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.montserrat)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            )
        )
    }
}


@Composable
fun HalfScreenHeightModifier(): Modifier {
    // Get the screen height from LocalConfiguration
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    // Calculate half of the screen height
    val halfScreenHeight = screenHeight / 2

    return Modifier.height(halfScreenHeight)
}

