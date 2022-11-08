package com.example.e_book.data

import android.media.Image
import com.example.e_book.R

data class dataProvider(val bookName:String) {
    val Book:String
    get() = bookName
}
data class dataProviderToCard(val bookImage: Int){
    val image:Int
    get() = bookImage
}
val bookList= listOf(
    dataProvider(
        bookName = "Fiction"
    ),
    dataProvider(
        bookName = "Most Popular"
    ),
    dataProvider(
        bookName = "Biography"
    ),
    dataProvider(
        bookName = "Environment"
    ),
    dataProvider(
        bookName = "Health & Fitness"
    ),
    dataProvider(
        bookName = "LifeStyle"
    ),
    dataProvider(
        bookName = "Science"
    ),
    dataProvider(
        bookName = "Technology"
    ),
    dataProvider(
        bookName = "Religion"
    ),
    dataProvider(
        bookName = "Business & Career"
    )

)

val bookImageList= listOf(
    dataProviderToCard(bookImage = R.drawable.baby),
    dataProviderToCard(bookImage = R.drawable.brain),
    dataProviderToCard(bookImage = R.drawable.fivesecond),
    dataProviderToCard(bookImage = R.drawable.gan),
    dataProviderToCard(bookImage = R.drawable.happiness),
    dataProviderToCard(bookImage = R.drawable.kids),
    dataProviderToCard(bookImage = R.drawable.livind),
    dataProviderToCard(bookImage = R.drawable.mother),
    dataProviderToCard(bookImage = R.drawable.napole),
    dataProviderToCard(bookImage = R.drawable.things),
    dataProviderToCard(bookImage = R.drawable.work),
)