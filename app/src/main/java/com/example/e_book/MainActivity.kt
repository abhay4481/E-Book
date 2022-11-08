package com.example.e_book

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.`class`.Screen
import com.example.e_book.`class`.Screens
import com.example.e_book.`class`.firebaseDataFile
import com.example.e_book.data.dataProvider
import com.example.e_book.screen.*
import com.example.e_book.ui.theme.EBookTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EBookTheme {
                Surface {
                     sastaSpotity()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun sastaSpotity(){
        val navController= rememberNavController()
        EBookTheme() {
            Scaffold(Modifier.background(color = androidx.compose.ui.graphics.Color.Gray),
                bottomBar = { BottomNavigation(navController = navController) }
            )
            {
                Navigation(navController=navController)
            }
        }
    }
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Navigation(navController:NavHostController) {
        NavHost(navController = navController,
            startDestination = Screens.Splash.route) {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }
            composable(BottomNavItem.Home.screen_route) {
                Home(navController)
            }

            composable(BottomNavItem.Favorite.screen_route) {
                Favorite()
            }

            composable(BottomNavItem.Download.screen_route) {
                Download()
            }
            composable(BottomNavItem.Audio.screen_route) {
                Audio()
            }
            composable(BottomNavItem.Setting.screen_route) {
                Setting(navController = navController)
            }
            composable(Screen.Profile.toString()) {
                ProfileScreen(navController = navController)
            }
            composable(Screen.About.toString()) {
//                About(navController = navController)
                PdfViewer(navController =navController)
            }
            composable(Screen.Setting.toString()) {
                Setting(navController = navController)
            }
//            composable(Screen.MoreBook.toString()){
//                Home(navController = navController)
//            }
            composable("bookList/{book}", arguments = listOf(navArgument("book"){
                type= NavType.StringType
            })) { backStackEntry ->
                backStackEntry.arguments?.getString("book")?.let { json ->
                    val book = Gson().fromJson(json, dataProvider::class.java)
                    MoreBookScreen(typeofBook = book, navController = navController)
                }
            }
            composable("Player/{PlayBook}", arguments = listOf(navArgument("PlayBook"){
                type=NavType.StringType
            })){
                    navBackStackEntry ->navBackStackEntry.arguments?.getString("PlayBook")?.let{
                    json ->val pdf =Gson().fromJson(json, firebaseDataFile::class.java)
//                PdfViewer( navController=navController,book=pdf  )
            }
            }
        }
    }}

@Composable
 fun BottomNavigation(
    navController: NavController
){ val items= listOf(
    BottomNavItem.Home,
    BottomNavItem.Favorite,
    BottomNavItem.Audio,
    BottomNavItem.Download,
    BottomNavItem.Setting
)

    BottomNavigation(
        backgroundColor = Color(color = R.color.BackgroundColor)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute=navBackStackEntry?.destination?.route
        items.forEach{
                item -> BottomNavigationItem(
            icon ={ Icon(painterResource(id = item.icon), contentDescription = item.title)},
            label={Text(text = item.title, fontSize = 10.sp)},
            selectedContentColor = androidx.compose.ui.graphics.Color.Black,
            unselectedContentColor = androidx.compose.ui.graphics.Color.White,
            alwaysShowLabel = true,
            selected = currentRoute==item.screen_route,
            onClick = {
                navController.navigate(item.screen_route){
                    navController.graph.startDestinationRoute?.let { screen_route ->popUpTo(screen_route){
                        saveState=true
                    } }
                    launchSingleTop=true
                    restoreState=true
                }
            }
        )
        }
    }

}
