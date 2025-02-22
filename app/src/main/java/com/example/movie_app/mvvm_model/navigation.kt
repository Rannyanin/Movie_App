package com.testproject.movie_app.mvvm_model

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testproject.movie_app.state_module.LoginScreen
import com.testproject.movie_app.state_module.MainScreen

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen() }
    }
}