package com.example.e_book

import android.app.Activity
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_book.`class`.Screens
import com.example.e_book.screen.Home
import com.example.e_book.screen.SplashScreen
import com.example.e_book.ui.theme.EBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(){
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }

    @Composable
    fun SetupNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screens.Splash.route
        ) {
            composable(route = Screens.Splash.route) {
                SplashScreen(navController = navController)
            }
            composable(route = Screens.Home.route) {
                Home()
            }
        }
    }

}
