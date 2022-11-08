package com.example.e_book.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.e_book.R
import com.example.e_book.ui.theme.EBookTheme

@Composable
fun SignUp(){
    var text by remember { mutableStateOf("") }
    var pass by remember {
        mutableStateOf("") }
    Box(Modifier.background(Color.White)) {
        Column(Modifier
            .padding(vertical = 100.dp)) {
            Text(text = "Sign Up",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 20.dp))
            OutlinedTextField(
                value = text,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.person1),
                        contentDescription = "emailIcon")
                },
                onValueChange = { text = it },
                label = { Text("Name") },
                placeholder = { Text(text = "Enter your name") },
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
            )
            OutlinedTextField(
                value = text,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription = "emailIcon")
                },
                onValueChange = { text = it },
                label = { Text("Email") },
                placeholder = { Text(text = "Enter your e-mail") },
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
            )
            OutlinedTextField(
                value = pass,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.person1),
                        contentDescription = "emailIcon")
                },
                onValueChange = { pass = it },
                label = { Text("Password") },
                placeholder = { Text(text = "PassWord") },
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Button(onClick = {}, modifier = Modifier
                .width(120.dp)
                .padding(vertical = 8.dp)
                .align(
                    Alignment.CenterHorizontally)) {
                Text(text = "Sign up")

            }
            Row(Modifier.padding(horizontal = 80.dp, vertical = 40.dp)) {
                Image(painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(50.dp))
                Spacer(Modifier.width(100.dp))
                Image(painter = painterResource(id = R.drawable.facebool),
                    contentDescription = "Google",
                    modifier = Modifier.size(50.dp))
            }
        }
    }
}
@Preview
@Composable
fun Preve(){
    EBookTheme{
        SignUp()
    }
}