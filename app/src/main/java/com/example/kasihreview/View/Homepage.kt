package com.example.kasihreview.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kasihreview.Model.MoviesDTO
import com.example.kasihreview.NavObjects.MovieDetails
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun homePage(navController: NavController, viewModel: KRviewModel){
    val popularMoviesState by viewModel.popularMoviesUIState.collectAsState()
    val currentSession by viewModel.currentSession.collectAsState()
    viewModel.getPopularMovies()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1F1D36))
            .padding(20.dp)
    ) {
        Row {
            Text(
                text = "Hai, ",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
            )
            currentSession.username?.let {
                Text(
                    text = it,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFE9A6A6),
                    modifier = Modifier
                )
            }
        }
        Text(
            text = "Tinjau atau lacak film yang sudah kamu tonton...",
            fontFamily = OpenSans,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = Color.White,
            modifier = Modifier
        )

        Column {
            Text(
                text = "Film Populer Bulan Ini",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
            ) {
                items(popularMoviesState.results){ movie ->
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500"+movie.poster_Url,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                movie.movie_Id?.let { viewModel.getMovieDetailsById(it) }
                                movie.movie_Id?.let {
                                    movie.title?.let { it1 ->
                                        MoviesDTO(
                                            it, it1, emptyList(), LocalDate.parse(movie.releaseYear).year,"Kosong",0.0,
                                            movie.poster_Url!!
                                        )
                                    }
                                }?.let { viewModel.postMovie(movieForPost = it) }
                                navController.navigate(MovieDetails)
                            }
                    )
                }

//                item {
//                    AsyncImage(
//                        model = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg",
//                        contentDescription = "kenken",
//                        modifier = Modifier
//                    )
//                }
            }

        }
    }
}