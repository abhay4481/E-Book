package com.example.e_book.screen

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_book.R
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.`class`.Screen
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

 var autha:FirebaseAuth=FirebaseAuth.getInstance()


@Composable
fun SignIn(navController: NavController){
    var email by remember { mutableStateOf("") }
    var pass by remember {
        mutableStateOf("") }
    val mContext = LocalContext.current
    Box(Modifier
        .size(height = 1200.dp, width = 450.dp)
        .background(color = Color.DarkGray)) {
        Column(Modifier
            .padding(vertical = 100.dp)) {
            Text(text = "Sign In",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 100.dp, vertical = 20.dp), color = Color.White)
            OutlinedTextField(
                value = email,
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.email),
                        contentDescription = "emailIcon", tint = Color.White)
                },
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text(text = "Enter your e-mail", color = Color.White) },
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 5.dp), singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White
                        )
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
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
                    unfocusedLabelColor = Color.White)
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            Button(onClick = {
                if(email.isEmpty()){
                    Toast.makeText(mContext, "Enter your email Please", Toast.LENGTH_SHORT).show()
                }
                else if(pass.isEmpty()){
                    Toast.makeText(mContext, "Enter your Password Please", Toast.LENGTH_SHORT).show()
                }else {
                autha.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithEmail:success")
                        Toast.makeText(mContext, "Loged in",
                            Toast.LENGTH_SHORT).show()
                        navController.navigate(BottomNavItem.Home.screen_route)
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(mContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
                }

            }, modifier = Modifier
                .width(120.dp)
                .padding(vertical = 8.dp)
                .align(
                    Alignment.CenterHorizontally)) {
                Text(text = "Sign In", style = MaterialTheme.typography.h5)

            }
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Button(onClick = { navController.navigate(Screen.SignUp.toString()) },
                modifier = Modifier
                    .size(width = 160.dp, height = 54.dp)
                    .align(
                        Alignment.CenterHorizontally))
            {
            Text(text ="Sign Up" , style = MaterialTheme.typography.h5 )
    }
    }
}

}