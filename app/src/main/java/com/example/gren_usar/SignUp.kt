package com.example.gren_usar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gren_usar.ui.theme.AppShapes
import com.example.gren_usar.ui.theme.GrenUsarTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrenUsarTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SignUpScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    GrenUsarTheme {
        SignUpScreen()
    }
}

@Composable
fun SignUpScreen() {
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
            Image(painter = painterResource(id = R.drawable.logo_), contentDescription = null)
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = "Signup to your account",
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .fillMaxWidth()
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
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /* Handle Create action */ },
                modifier = Modifier
                    .width(311.dp)
                    .height(48.dp)
                    .border(2.dp, Color.White, shape = AppShapes.small)
                    .background(shape = AppShapes.small, color = Color.Red)
            ) {
                Text(text = "Create", color = Color.White)
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
            .width(311.dp)
            .height(48.dp)
            .background(Color.Transparent, shape = AppShapes.small)
            .padding((0.dp), 0.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .background(
                            Color.Transparent,
                            shape = AppShapes.small
                        )
                        .border(2.dp, Color.White, shape = AppShapes.small)
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
