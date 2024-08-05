package com.example.gren_usar

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.gren_usar.ui.theme.AppShapes
import com.example.gren_usar.ui.theme.GrenUsarTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : ComponentActivity() {

}

@Composable
fun LoginScreen(navController: NavController, onLoginSuccess: () -> Unit) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val auth = Firebase.auth
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
            Spacer(modifier = Modifier.height(20.dp))
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

            CustomTextField(value = email.value, onValueChange = { email.value = it }, hint = "Email")
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(value = password.value, onValueChange = { password.value = it }, hint = "Enter Password", isPassword = true)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    loginEmailPassUser(
                        email.value,
                        password.value,
                        navController,
                        auth)
                          },
                modifier = Modifier
                    .size(width = 201.dp, height = 48.dp)
                    .background(color = Color.Transparent, shape = AppShapes.small)
                    .border(2.dp, Color.White, shape = AppShapes.small)
            ) {
                Text(text = "Login", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                onClick = { /* Handle forgot password action */ },
                modifier = Modifier
                    .size(width = 311.dp, height = 48.dp)
                    .background(color = Color.Transparent, shape = AppShapes.small)
            ) {
                Text(text = "Forgot Your Password?", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box {
                OutlinedButton(
                    onClick = { navController.navigate("signup") },
                    modifier = Modifier
                        .size(width = 151.dp, height = 48.dp)
                        .background(color = Color.Transparent, shape = AppShapes.small)
                        .border(2.dp, Color.White, shape = AppShapes.small)
                ) {
                    Text(text = "Sign Up", color = Color.White)
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    GrenUsarTheme {
//        LoginScreen(rememberNavController())
    }
}

fun loginEmailPassUser(email: String, password: String, navController: NavController, auth: FirebaseAuth) {
    if (email.isNotEmpty() && password.isNotEmpty()) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                navController.navigate("Home") // Navigate to home screen
            }
            .addOnFailureListener { exception ->
                Toast.makeText(navController.context, "Authentication failed: ${exception.message}", Toast.LENGTH_LONG).show()
            }
    } else {
        Toast.makeText(navController.context, "Email and Password must not be empty", Toast.LENGTH_SHORT).show()
    }
}
