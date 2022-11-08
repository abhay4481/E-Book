package com.example.e_book.screen

import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_book.R
import com.example.e_book.`class`.BottomNavItem
import com.example.e_book.`class`.firebaseDataFile
import com.pspdfkit.jetpack.compose.DocumentView
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PdfViewer(navController: NavController){
    Column( Modifier.padding(vertical = 20.dp)) {
        Row(Modifier.padding(vertical = 6.dp, horizontal = 10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.prev ),
                contentDescription = null,
                Modifier
                    .size(50.dp)
                    .padding(vertical = 10.dp)
                    .clickable(onClick = { navController.navigate(BottomNavItem.Home.screen_route) })
            )
            Text(
                text ="Time pass",
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
                style = MaterialTheme.typography.h5
            )
        }
        val context= LocalContext.current
        val time= Uri.parse("https://firebasestorage.googleapis.com/v0/b/e-book-4321e.appspot.com/o/fiction%2FBoundaries_%20When%20to%20Say%20Yes.pdf?alt=media&token=7320d348-9cb2-4e05-a1b7-7dd17c799cc6")

        Greeting(uri = time, context = context)

    }

}

 
@OptIn(ExperimentalPSPDFKitApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(uri: Uri, context: Context) {
    Surface {
        val documentUri = remember { MediaStore.getDocumentUri(context, uri) }
        if (documentUri != null) {
            DocumentView(
                documentUri = documentUri,
                modifier = Modifier
                    .height(100.dp)
                    .padding(16.dp)
            )
        }
        else{
            Text(text = "Document is not loaded")
        }
    }
}