package com.example.basicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.basicnavigation.ui.theme.BasicNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        // Layar Home (Praktik 1)
        composable<Home> {
            HomeScreen(navController = navController)
        }
        // Layar Profile (Praktik 1)
        composable<Profile> {
            ProfileScreen(
                navController = navController,
                onNavigateToDetail = { navController.navigate(Detail) }
            )
        }
        // Layar Detail (Praktik 1)
        composable<Detail> {
            DetailScreen(navController = navController)
        }
        // Layar Formulir Pesanan (Praktik 2)
        composable<OrderForm> {
            OrderFormScreen(
                navController = navController,
                onNavigateToDetails = { productName, quantity, price ->
                    navController.navigate(
                        OrderDetails(productName, quantity, price)
                    )
                }
            )
        }
        // Layar Detail Pesanan (Praktik 2)
        composable<OrderDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<OrderDetails>()
            OrderDetailsScreen(
                navController = navController,
                productName = args.productName,
                quantity = args.quantity,
                price = args.price
            )
        }
    }
}
