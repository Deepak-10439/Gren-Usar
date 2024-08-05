package com.example.gren_usar

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.gren_usar.ui.theme.AppShapes
import com.example.gren_usar.ui.theme.GrenUsarTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    GrenUsarTheme {
        SignUpScreen(rememberNavController())
    }
}

@Composable
fun SignUpScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
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
            Image(painter = painterResource(id = R.drawable.lgo), contentDescription = null, modifier = Modifier.height(180.dp).width(250.dp).padding(10.dp,0.dp,0.dp,0.dp))
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(value = firstName.value, onValueChange = { firstName.value = it }, hint = "First Name")
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(value = lastName.value, onValueChange = { lastName.value = it }, hint = "Last Name")
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(value = email.value, onValueChange = { email.value = it }, hint = "Email/Mobile Number")
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(value = password.value, onValueChange = { password.value = it }, hint = "Enter Password", isPassword = true)
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { CreateUserAccount(firstName.value, lastName.value, email.value, password.value, navController) },
                modifier = Modifier
                    .width(151.dp)
                    .height(48.dp)
                    .border(2.dp, Color.White, shape = AppShapes.small)
                    .background(shape = AppShapes.small, color = Color(0xFF33907C))
            ) {
                Text(text = "Create Account", color = Color.White)
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isPassword: Boolean = false
) {
    Box(
        modifier = Modifier
            .widthIn(250.dp, 311.dp)
            .height(48.dp)
            .background(Color.Transparent, shape = AppShapes.small)
            .padding(0.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(color = Color.White),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .background(Color.Transparent, shape = AppShapes.small)
                        .border(2.dp, Color.White, shape = AppShapes.large)
                        .padding(start = 20.dp)
                        .fillMaxSize()
                ) {
                    if (value.isEmpty()) {
                        Text(text = hint, color = Color.White.copy(alpha = 1.0f))
                    }
                    innerTextField()
                }
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

fun CreateUserAccount(firstName: String, lastName: String, email: String, password: String, navController: NavController) {
    if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                Toast.makeText(navController.context, "Account Created Successfully", Toast.LENGTH_LONG).show()
                navController.navigate(GrenScreen.Login.name) // Navigate to login screen
            }
            .addOnFailureListener { exception ->
                Toast.makeText(navController.context, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    } else {
        Toast.makeText(navController.context, "All fields must be filled", Toast.LENGTH_SHORT).show()
    }
}
