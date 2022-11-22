package com.example.e_book.screen

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_book.R
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.ui.theme.EBookTheme
import java.util.regex.Matcher
import java.util.regex.Pattern
var username by  mutableStateOf("")
@Composable
fun SignUp(navController: NavController){
    var email by remember { mutableStateOf("") }

    var pass by remember {
        mutableStateOf("") }
    val context= LocalContext.current
    Box(Modifier.background(Color.DarkGray).size(height = 1200.dp, width = 450.dp)) {
        Column(Modifier
            .padding(vertical = 100.dp)) {
            Text(text = "Sign Up",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 20.dp), color = Color.White)
            OutlinedTextField(
                value = username,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.person1),
                        contentDescription = "username", tint = Color.White)
                },
                onValueChange = { username = it },
                label = { Text("Name", color = Color.White) },
                placeholder = { Text(text = "Enter your name", color = Color.White) },
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp)
                , singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
            OutlinedTextField(
                value = email,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription = "emailIcon" , tint = Color.White)
                },
                onValueChange = { email = it },
                label = { Text("Email", color = Color.White) },
                placeholder = { Text(text = "Enter your e-mail", color = Color.White) },
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )
            OutlinedTextField(
                value = pass,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.password),
                        contentDescription = "emailIcon", tint = Color.White)
                },
                onValueChange = { pass = it },
                label = { Text("Password", color = Color.White) },
                placeholder = { Text(text = "PassWord", color = Color.White) },
                modifier = Modifier.padding(horizontal = 40.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White
                )
            )

            Button(onClick = {
                if(email.isEmpty()){
                    Toast.makeText(context, "Enter your email Please", Toast.LENGTH_SHORT).show()
                }
                else if(pass.isEmpty()){
                    Toast.makeText(context, "Enter your Password Please", Toast.LENGTH_SHORT).show()
                }else {
                    autha.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                Toast.makeText(context, "Successfully Create Account",
                                    Toast.LENGTH_SHORT).show()
                                navController.navigate(BottomNavItem.Home.screen_route)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                            }
                        }
                }


            }, modifier = Modifier
                .width(120.dp)
                .padding(vertical = 8.dp)
                .align(
                    Alignment.CenterHorizontally)) {
                Text(text = "Sign up", style = MaterialTheme.typography.h5)

            }
        }
    }
}
