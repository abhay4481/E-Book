package com.example.e_book.screen
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.e_book.R
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.data.*
import com.google.gson.Gson


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController){
    Scaffold(bottomBar = { com.example.e_book.BottomNavigation(navController = navController) }) {
        HomeTime(navController = navController)
    }
}
@Composable
fun HomeTime(navController: NavController){

        Box(Modifier.background(color = Color(color = R.color.BackgroundColor) )) {
            Image(painter = painterResource(id = R.drawable.back2), contentDescription = null, contentScale = ContentScale.FillBounds, modifier = Modifier.size(height = 1000.dp, width = 400.dp))
            Column {
                Text(text = "HOME", style = MaterialTheme.typography.h4, modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp), color = Color.White, fontWeight = FontWeight.Bold)
                SlidingList(navController)
                SlidingImageList(navController)
            }
        }
}



@Composable
fun SlidingList(navController:NavController) {
    fun navigateToBookList(bookList: dataProvider){
        val bookJason= Gson().toJson(bookList)
        navController.navigate("bookList/$bookJason")
    }
    Column(){
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        )
        {
            items(items=bookList){
                item ->
                AlingmentBookList(text = item.Book,
                    Modifier.clickable (onClick = {navigateToBookList(item) })
                )
            }
        }
    }
}
val QuickSand=FontFamily(
    Font(R.font.acmeregular)
)
val Typography=Typography(h5= androidx.compose.ui.text.TextStyle(fontFamily = QuickSand, fontWeight = FontWeight.Normal, fontSize = 10.sp))
@Composable
fun AlingmentBookList(text: String, modifier: Modifier=Modifier) {
        Text(text =text, style = MaterialTheme.typography.h5, modifier = modifier.padding(horizontal = 5.dp, vertical = 7.dp), color = Color.White)
}
@Composable
fun SlidingImageList(navController: NavController) {
    fun navigateToBookList(bookList: dataProviderToCard){
        val BookPdfView= Gson().toJson(bookList)
        navController.navigate("bookPdfList/$BookPdfView")
    }
    Column(){
        LazyVerticalGrid(
            contentPadding = PaddingValues(vertical = 5.dp , horizontal = 8.dp),
            columns=GridCells.Fixed(2)
        )
        {
            items(items = bookImageList){
                item ->
                AlingmentBookImageList(image = item.image, Modifier.clickable {
                    navigateToBookList(item)
                })
            }
        }
    }
}
@Composable
fun AlingmentBookImageList(image: Int, modifier: Modifier) {
    Card(Modifier
        .padding(vertical = 8.dp, horizontal = 17.dp)
        .height(190.dp)
        .width(30.dp) , elevation = 4.dp , backgroundColor = Color.LightGray) {
       Image(painter = painterResource(id =image ), contentDescription ="Image" , contentScale = ContentScale.FillBounds, modifier = modifier
           .height(150.dp)
           .width(50.dp)
           )
    }
}