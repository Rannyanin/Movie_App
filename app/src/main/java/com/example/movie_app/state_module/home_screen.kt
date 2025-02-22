package com.testproject.movie_app.state_module

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movie_app.R

@Composable
fun HomeScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Header Section with Search Bar
        HeaderSection(searchQuery) { newSearchQuery ->
            searchQuery = newSearchQuery
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Featured Movies Section
        Text("Featured Movies", style = MaterialTheme.typography.headlineSmall, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        FeaturedMoviesSection(navController)

        Spacer(modifier = Modifier.height(16.dp))

        // Categories Section
        Text("Categories", style = MaterialTheme.typography.headlineSmall, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        CategoriesSection()
    }
}

@Composable
fun HeaderSection(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    Column {
        Text(
            text = "MovieApp",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            placeholder = { Text("Search movies...") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun FeaturedMoviesSection(navController: NavController) {
    val featuredMovies = listOf(
        R.drawable.movie1,
        R.drawable.movie2,
        R.drawable.movie3,
        R.drawable.movie4,
        R.drawable.movie5,
        R.drawable.movie6
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(featuredMovies) { movie ->
            MoviePoster(posterRes = movie, navController = navController)
        }
    }
}

@Composable
fun MoviePoster(posterRes: Int, navController: NavController) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
            .clickable {
                navController.navigate("movieDetail/$posterRes")
            }
    ) {
        Image(
            painter = painterResource(id = posterRes),
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CategoriesSection() {
    val categories = listOf("Popular", "Trending", "Top Rated", "Upcoming", "New Releases", "Action", "Comedy", "Drama")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            CategoryChip(categoryName = category)
        }
    }
}

@Composable
fun CategoryChip(categoryName: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { },
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
