package com.example.gren_usar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gren_usar.ui.theme.GrenUsarTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrenUsarTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    private fun SplashScreen() {
        LaunchedEffect(key1 = true) {
            delay(2000) // Adjusted delay for demonstration
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish() // Finish SplashActivity to remove it from the back stack
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF208679)),
            contentAlignment = Alignment.Center
        ) {
            val screenHeight = maxHeight
            val screenWidth = maxWidth

            val topMargin = 191.dp
            val bottomMargin = 255.dp
            val leftMargin = 34.dp

            val imageHeight = 366.dp
            val imageWidth = 341.dp

            val verticalMargin = topMargin + bottomMargin

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topMargin,
                        bottom = bottomMargin,
                        start = leftMargin
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(imageWidth)
                        .height(imageHeight)
                        .align(Alignment.TopStart)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewSplashScreen() {
        GrenUsarTheme {
            SplashScreen()
        }
    }
}
