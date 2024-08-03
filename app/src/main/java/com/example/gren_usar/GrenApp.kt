package com.example.gren_usar

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class GrenScreen {
    Login,
    Home,
    Items,
    Cart,
    Signup,
    Browse,
    Store,
    OrderHistory,
    Profile,
    ProductDetail,
    Check_out_Screen_1,
    Check_out_Screen_2
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GrenApp(
    mainViewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = GrenScreen.Check_out_Screen_2.name
    ) {
        composable(route = GrenScreen.Login.name) {
            LoginScreen(navController, onLoginSuccess = {
                navController.navigate(GrenScreen.Home.name) {
                    popUpTo(GrenScreen.Login.name) { inclusive = true }
                }
            })
        }

        composable(route = GrenScreen.Home.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) },
                topBar = { TopAppBar(navController) }
            ) { innerPadding ->
                HomeScreen(
                    mainViewModel = mainViewModel,
                    onCategoryClick = {
                        mainViewModel.updateSelectedCategory(it)
                        navController.navigate(GrenScreen.Items.name)
                    },
                    navController = navController
                )
            }
        }

        composable(route = GrenScreen.Items.name) {
            Scaffold(
                topBar = { ItemTopBar() },
                bottomBar = { BottomAppBar(navController) }
            ) {
                ItemsScreen(mainViewModel = mainViewModel)
            }
        }

        composable(route = GrenScreen.Signup.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) }
            ) {
                SignUpScreen(navController)
            }
        }

        composable(route = GrenScreen.Browse.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) }
            ) {
                BrowseScreen()
            }
        }

        composable(route = GrenScreen.Store.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) }
            ) {
                StoreScreen()
            }
        }

        composable(route = GrenScreen.OrderHistory.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) }
            ) {
                OrderHistoryScreen()
            }
        }

        composable(route = GrenScreen.Profile.name) {
            Scaffold(
                bottomBar = { BottomAppBar(navController) }
            ) {
                ProfileScreen()
            }
        }

        composable(route = GrenScreen.ProductDetail.name) {
            ProductDetail(navController = navController)
        }

        // Add routes for checkout screens
        composable(route = GrenScreen.Check_out_Screen_1.name) {
            Scaffold(
                topBar = { TopApp_Checkout(text = "My Cart") },
            ) {
                CheckOutScreen_1(navController = navController)
            }
        }

        composable(route = GrenScreen.Check_out_Screen_2.name) {
            Scaffold(
                topBar = { TopApp_Checkout(text = "Take a Photo \n  of Garbage") }
            ) {
                Checkout_Screen_2()
            }
        }
    }
}
