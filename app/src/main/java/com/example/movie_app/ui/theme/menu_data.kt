package com.example.movie_app.ui.theme

sealed class MenuItem {
    object Home : MenuItem()
    object Search : MenuItem()
    object AboutUs : MenuItem()
    object Settings : MenuItem()
}
