package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kasihreview.Model.MovieDetails
import com.example.kasihreview.Model.Movies
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.UlasanPage
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

@Composable
fun reviewDetail(navController: NavController, viewModel: KRviewModel){
    var rating = 4.5
    val movie by viewModel.movieDetailsState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D36))
            .padding(15.dp)
    ) {
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500"+movie.poster_Url,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .width(150.dp)
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "4.4",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFE9A6A6)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        when (rating) {
                            0.5 -> Image(imageVector = ImageVector.vectorResource(R.drawable.half_star), contentDescription = "")
                            1.0 -> Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                            1.5 -> {
                                Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                Image(imageVector = ImageVector.vectorResource(R.drawable.half_star), contentDescription = "")
                            }
                            2.0 -> {
                                for (i in 1..2) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                            }
                            2.5 -> {
                                for (i in 1..2) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                                Image(imageVector = ImageVector.vectorResource(R.drawable.half_star), contentDescription = "")
                            }
                            3.0 -> {
                                for (i in 1..3) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                            }
                            3.5 -> {
                                for (i in 1..3) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                                Image(imageVector = ImageVector.vectorResource(R.drawable.half_star), contentDescription = "")
                            }
                            4.0 -> {
                                for (i in 1..4) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                            }
                            4.5 -> {
                                for (i in 1..4) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                                Image(imageVector = ImageVector.vectorResource(R.drawable.half_star), contentDescription = "")
                            }

                            5.0 -> {
                                for (i in 1..5) {
                                    Image(imageVector = ImageVector.vectorResource(R.drawable.star), contentDescription = "")
                                }
                            }

                        }
                    }
                }
            }

            Column {

                movie.title?.let {
                    Text(
                        text = it,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                movie.releaseYear?.let {
                    Text(
                        text = it,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )


                movie.description?.let {
                    Text(
                        text = it,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

            }


        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(0.19f))
        )

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Text(
            text = "Ulasan Anda",
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
        )

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )


    }
}