package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kasihreview.Model.MovieDetails
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

data class GenreDetails(
    var id: Int,
    var name: String
)

val genreList: List<GenreDetails> = listOf(
    GenreDetails(id = 28, name = "Action"),
    GenreDetails(id = 12, name = "Adventure"),
    GenreDetails(id = 16, name = "Animation"),
    GenreDetails(id = 35, name = "Comedy"),
    GenreDetails(id = 80, name = "Crime"),
    GenreDetails(id = 99, name = "Documentary"),
    GenreDetails(id = 18, name = "Drama"),
    GenreDetails(id = 10751, name = "Family"),
    GenreDetails(id = 14, name = "Fantasy"),
    GenreDetails(id = 36, name = "History"),
    GenreDetails(id = 27, name = "Horror"),
    GenreDetails(id = 10402, name = "Music"),
    GenreDetails(id = 9648, name = "Mystery"),
    GenreDetails(id = 10749, name = "Romance"),
    GenreDetails(id = 878, name = "Science Fiction"),
    GenreDetails(id = 10770, name = "TV Movie"),
    GenreDetails(id = 53, name = "Thriller"),
    GenreDetails(id = 10752, name = "War"),
    GenreDetails(id = 37, name = "Western")
)


@Composable
fun searchPage(navController: NavController, VM: KRviewModel){
    val result by VM.moviesSearch.collectAsState()
    val genres by VM.accumulatedGenre.collectAsState()
    var genreToggle by remember {
        mutableStateOf(false)
    }
    var input by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D36))
    ) {
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            TextField(
                value = input,
                onValueChange = { change ->
                    input = change
                },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(
                        text = "Search",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF8F8E9A)
                    )
                },
                leadingIcon = {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.searchicon),
                        contentDescription = ""
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFF1F1D36),
                    focusedContainerColor = Color(0xFF1F1D36),
                    focusedIndicatorColor = Color.Transparent,   // no line when focused
                    unfocusedIndicatorColor = Color.Transparent, // no line when not focused
                    disabledIndicatorColor = Color.Transparent   // no line when disabled
                ),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFF8F8E9A),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .width(330.dp)
            )

            Image(
                imageVector = ImageVector.vectorResource(R.drawable.filter_list),
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        genreToggle = !genreToggle
                    }
            )
        }

        Spacer(
            modifier = Modifier
                .height(5.dp)
        )

        if (genreToggle == true) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 0..2) {
                        genreButton(genreList[i],VM)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 4..6) {
                        genreButton(genreList[i],VM)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 7..9) {
                        genreButton(genreList[i],VM)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 10..12) {
                        genreButton(genreList[i],VM)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 13..15) {
                        genreButton(genreList[i],VM)
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (i in 16..18) {
                        genreButton(genreList[i],VM)
                    }
                }
                genreButton(genreList[3],VM)
            }
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Button(
                onClick = {
                    if (genreToggle == false) {
                        VM.getMoviesByName(input)
                    }else {
                        VM.getMoviesByGenres(genres)
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE9A6A6),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .width(100.dp)
                    .height(35.dp)
            ) {
                Text(
                    text = "Search",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1F1D36),
                )
            }
            Spacer(
                modifier = Modifier
                    .width(25.dp)
            )
        }


        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(result.results.chunked(2)) {movieRow ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    movieRow.forEach { movie ->
                        Column(
                            //verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .width(180.dp)
                                .clickable {
                                    movie.movie_Id?.let { VM.getMovieDetailsById(it) }
                                    navController.navigate(com.example.kasihreview.NavObjects.MovieDetails)
                                }
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500"+movie.poster_Url,
                                contentDescription = movie.title,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                            )
                            movie.title?.let {
                                Text(
                                    text = it,
                                    fontFamily = OpenSans,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF8F8E9A)
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(5.dp)
                            )
                            movie.releaseYear?.let {
                                Text(
                                    text = it,
                                    fontFamily = OpenSans,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF8F8E9A)
                                )
                            }
                        }
                    }


                }


            }
        }
    }
}