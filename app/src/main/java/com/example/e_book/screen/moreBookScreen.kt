package com.example.e_book.screen

import android.content.ContentValues
import android.transition.Scene
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.e_book.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.`class`.Screen
import com.example.e_book.`class`.firebaseDataFile
import com.example.e_book.data.dataProvider
import com.example.e_book.data.dataProviderToCard
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class MainViewModel   {
    //    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val temList= mutableListOf<firebaseDataFile>()
    init {
        FetchDataFromDatabase()
    }
    private fun FetchDataFromDatabase(){


        FirebaseDatabase.getInstance().reference.child("fiction")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    for(DataSnap in snapshot.children) {
                        val Song = DataSnap.getValue(firebaseDataFile::class.java)

                        if (Song != null) {
                            temList.add(Song)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w(ContentValues.TAG, "loadPost:onCancelled")
                }
            }
            )
    }}
val arr=MainViewModel()
@Composable
fun MoreBookScreen(typeofBook:dataProvider, navController: NavController) {
    Box() {
        Image(painter = painterResource(id = R.drawable.back2), contentDescription = null, contentScale = ContentScale.FillBounds, modifier = Modifier.size(height = 1000.dp, width = 400.dp))

        Column(Modifier.padding(vertical = 20.dp)) {
            Row(Modifier.padding(vertical = 6.dp, horizontal = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.pre),
                    contentDescription = null,
                    Modifier
                        .size(50.dp)
                        .padding(vertical = 10.dp)
                        .clickable(onClick = { navController.navigate(BottomNavItem.Home.screen_route) })
                )
                Text(
                    text = typeofBook.bookName,
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
            }
            BookCard(name = typeofBook.array, navController)
//        Bookshelf(arr.temList)
        }

    }
}

@Composable
fun BookCard(name: List<dataProviderToCard>, navController: NavController ){
    fun navigateToSingerPlaySong(singer: dataProviderToCard){
        val PlayBook= Gson().toJson(singer)
        navController.navigate("Player/$PlayBook")
    }
    Column(){
        LazyVerticalGrid(
            contentPadding = PaddingValues(vertical = 5.dp , horizontal = 8.dp),
            columns= GridCells.Fixed(2)
        )
        {
            items(items = name){names->
            AlingmentBook(names, navController , Modifier.clickable(onClick = {navigateToSingerPlaySong(names)}))
            }
        }
    }
}
@Composable
fun AlingmentBook(name: dataProviderToCard, navController: NavController, modifier: Modifier) {
    Card(modifier
        .padding(vertical = 8.dp, horizontal = 17.dp)
        .height(190.dp)
        .width(30.dp)
         , elevation = 4.dp , backgroundColor = Color.LightGray) {
        Image(painter = rememberAsyncImagePainter(model = name.image), contentDescription ="Image" , contentScale = ContentScale.FillBounds, modifier = Modifier
            .height(150.dp)
            .width(50.dp)
        )
    }
}
