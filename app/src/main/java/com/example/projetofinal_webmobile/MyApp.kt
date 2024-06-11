package com.example.webmobile_api

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.webmobile_api.ui.theme.WebMobileAPITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    WebMobileAPITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "login") {
                composable("login") { LoginScreen(navController) }
                composable("home/{userName}") { backStackEntry ->
                    val userName = backStackEntry.arguments?.getString("userName")
                    HomeScreen(navController, userName)
                }
            }
        }
    }
}
