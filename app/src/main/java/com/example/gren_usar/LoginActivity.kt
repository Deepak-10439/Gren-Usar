package com.example.gren_usar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gren_usar.ui.theme.AppShapes
import com.example.gren_usar.ui.theme.GrenUsarTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrenUsarTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "loginScreen") {
                    composable("loginScreen") {
                        LoginScreen(navController)
                    }
                    // Add other destinations as needed
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF33907C))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(id = R.drawable.logo_), contentDescription = null)
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = "Login to Your Account",
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(value = email.value, onValueChange = {email.value = it}, hint = "Email")
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(value = password.value, onValueChange = { password.value = it }, hint = "Enter Password", isPassword = true)
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("homeScreen") }, // Navigate to home screen
                modifier = Modifier
                    .size(width = 311.dp, height = 48.dp)
                    .background(color = Color.Transparent, shape = AppShapes.small)
            ) {
                Text(text = "Login", color = Color(0xFF33907C))
            }

            Text(
                text = "Forgot your password?",
                color = Color.White,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .clickable { /* Handle forgot password */ }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate("signupScreen") }, // Navigate to sign-up screen
                modifier = Modifier
                    .size(width = 311.dp, height = 48.dp)
                    .background(color = Color.Transparent, shape = AppShapes.small)
            ) {
                Text(text = "Sign Up", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    GrenUsarTheme {
        LoginScreen(rememberNavController())
    }
}


