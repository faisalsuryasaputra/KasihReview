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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kasihreview.Model.MovieSearchResult
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

@Composable
fun watchListPage(navController: NavController,VM: KRviewModel){

    val watchList by VM.accountWatchList.collectAsState()
    val account by VM.currentSession.collectAsState()
    account.id?.let { VM.getWatchListByUserId(it) }

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

        Text(
            text = "Watch List",
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(watchList.movies.chunked(2)) {movieRow ->
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
                                    movie.movieId.let { VM.getMovieDetailsById(it) }
                                    navController.navigate(com.example.kasihreview.NavObjects.MovieDetails)
                                }
                        ) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500"+movie.posterUrl,
                                contentDescription = movie.movieTitle,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                            )
                            Text(
                                text = movie.movieTitle,
                                fontFamily = OpenSans,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF8F8E9A)
                            )
                            Spacer(
                                modifier = Modifier
                                    .height(5.dp)
                            )

                            Text(
                                text = movie.releaseYear.toString(),
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