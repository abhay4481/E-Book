package com.example.e_book.screen

import android.media.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.e_book.R

@Composable
fun Home(){
    Scaffold() {
        TopAppBar() {
            Row() {
                Text(text = "E-Book",
                    Modifier
                        .size(20.dp)
                        .weight(1f)
                        .padding(horizontal = 25.dp))
                Icon( Icons.Default.Search, contentDescription ="Search" )
            }
        }
    }
}