package com.example.gren_usar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.gren_usar.data.DataSource

@Composable
fun ItemsScreen(mainViewModel: MainViewModel) {
    val grenUiState by mainViewModel.uiState.collectAsState()
    
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(DataSource.loadItems(grenUiState.selectedCategory)) {
            ItemCard(
                stringResourceId = it.stringResourceId,
                imageResourceId = it.imageResourceId,
                Price = it.Price,
                value = it.footPrint
            )
        }
    }
}

@Composable
fun ItemCard(
    stringResourceId: Int,
    imageResourceId: Int,
    Price: Int,
    value: Float
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) { // Reduced padding here
        Card(
            Modifier
                .border(
                    width = 2.dp,
                    color = Color(0x1A000000),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .padding(1.dp)
                .width(150.dp)
                .height(200.dp)
                .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))
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
                            text = "$$Price",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.montserrat)),
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF33907C)
                            )
                        )
                    }

//                    Spacer(modifier = Modifier.height(5.dp))
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
