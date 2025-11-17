package com.example.kasihreview.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kasihreview.Model.genre
import com.example.kasihreview.ViewModel.KRviewModel




@Composable
fun testingSearch(VM: KRviewModel){
    val result by VM.moviesSearch.collectAsState()
    val genres by VM.accumulatedGenre.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        var input by remember {
            mutableStateOf("")
        }
        TextField(
            value = input,
            onValueChange = { change ->
                input = change
            }
        )
        Row {
            for (i in 0..3) {
                genreButton(genreList[i],VM)
            }
        }
        Row {
            for (i in 4..6) {
                genreButton(genreList[i],VM)
            }
        }
        Row {
            for (i in 7..9) {
                genreButton(genreList[i],VM)
            }
        }
        Row {
            for (i in 10..12) {
                genreButton(genreList[i],VM)
            }
        }
        Row {
            for (i in 13..15) {
                genreButton(genreList[i],VM)
            }
        }
        Row {
            for (i in 16..18) {
                genreButton(genreList[i],VM)
            }
        }


        Button(
            onClick = {
//                VM.getMoviesByName(input)
                VM.getMoviesByGenres(genres)
            }
        ) {
            Text(text = "Search")
        }

        LazyColumn {
            items(result.results) {movie ->
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500"+movie.poster_Url,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                )
            }
        }
    }

}