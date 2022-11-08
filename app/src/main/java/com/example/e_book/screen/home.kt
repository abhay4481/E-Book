package com.example.e_book.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
import com.example.e_book.data.bookImageList
import com.example.e_book.data.bookList
import com.example.e_book.data.dataProvider
import com.google.gson.Gson
import java.time.format.TextStyle
import kotlin.text.Typography

@Composable
fun Home(navController: NavController){
        Box(Modifier.background(color = Color(color = R.color.BackgroundColor) )) {
            Column {
                SearchBar()
                SlidingList(navController)
                SlidingImageList()

            }
        }
}

@Composable
fun SearchBar(modifier: Modifier=Modifier )
{
    Column {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text(text = "Search")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(8.dp),
        )
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
    Card(modifier
        .padding(vertical = 20.dp, horizontal = 3.dp)
        .height(50.dp), elevation = 10.dp , backgroundColor = Color.White, shape = RoundedCornerShape(20.dp)) {
        Text(text =text, style = MaterialTheme.typography.h5, modifier = Modifier.padding(horizontal = 5.dp, vertical = 7.dp))
    }
}
@Composable
fun SlidingImageList() {

    Column(){
        LazyVerticalGrid(
            contentPadding = PaddingValues(vertical = 5.dp , horizontal = 8.dp),
            columns=GridCells.Fixed(2)
        )
        {
            items(bookImageList){
                item ->
                AlingmentBookImageList(image = item.image)
            }
        }
    }
}
@Composable
fun AlingmentBookImageList(image: Int) {
    Card(Modifier
        .padding(vertical = 8.dp, horizontal = 17.dp)
        .height(190.dp)
        .width(30.dp) , elevation = 4.dp , backgroundColor = Color.LightGray) {
       Image(painter = painterResource(id =image ), contentDescription ="Image" , contentScale = ContentScale.FillBounds, modifier = Modifier
           .height(150.dp)
           .width(50.dp)
           )
    }
}