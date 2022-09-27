package com.example.e_book.`class`

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object Home : Screens("home_screen")
}