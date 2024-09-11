package com.example.gren_usar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF33907C), Color(0xFF1E5547))
                )
            )
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.lgo),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(120.dp)
                    .width(200.dp)
                    .padding(bottom = 16.dp)
            )
            
            Text(
                text = "Create Account",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(value = firstName.value, onValueChange = { firstName.value = it }, hint = "First Name")
            CustomTextField(value = lastName.value, onValueChange = { lastName.value = it }, hint = "Last Name")
            CustomTextField(value = email.value, onValueChange = { email.value = it }, hint = "Email/Mobile Number")
            CustomTextField(value = password.value, onValueChange = { password.value = it }, hint = "Enter Password", isPassword = true)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { CreateUserAccount(firstName.value, lastName.value, email.value, password.value, navController) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = AppShapes.medium
            ) {
                Text(text = "Sign Up", color = Color(0xFF33907C), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Already have an account?", color = Color.White)
                TextButton(onClick = { navController.navigate(GrenScreen.Login.name) }) {
                    Text("Log In", color = Color.White, fontWeight = FontWeight.Bold)
                }
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
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(hint, color = Color.White.copy(alpha = 0.7f)) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        textStyle = TextStyle(color = Color.White),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
            focusedLabelColor = Color.White,
            cursorColor = Color.White
        ),
        shape = AppShapes.medium,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
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
