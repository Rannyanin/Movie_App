package com.testproject.movie_app.state_module

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movie_app.state_module.MovieDetailScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                MenuBar(navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // Navigation Host to manage screens
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(navController = navController) }
                composable("search") { SearchScreen(navController = navController) }
                composable("about") { AboutUsScreen(navController = navController) }
                composable("settings") { SettingsScreen(navController = navController) }
                // ✅ Add Movie Detail Screen Route
                composable("movieDetail/{movieId}") { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                    if (movieId != null) {
                        MovieDetailScreen(movieId, navController)
                    } else {
                        Text("Movie not found", style = MaterialTheme.typography.headlineMedium)
                    }
                }

            }
            }
        }
    }


@Composable
fun MenuBar(navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MenuBarButton(
            icon = Icons.Default.Home,
            label = "Home",
            isSelected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        MenuBarButton(
            icon = Icons.Default.Search,
            label = "Search",
            isSelected = currentRoute == "search",
            onClick = { navController.navigate("search") }
        )
        MenuBarButton(
            icon = Icons.Default.Info,
            label = "About Us",
            isSelected = currentRoute == "about",
            onClick = { navController.navigate("about") }
        )
        MenuBarButton(
            icon = Icons.Default.Settings,
            label = "Settings",
            isSelected = currentRoute == "settings",
            onClick = { navController.navigate("settings") }
        )
    }
}

@Composable
fun MenuBarButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

