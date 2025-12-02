package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.MovieDetails
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

@Composable
fun ulasanPage(navController: NavController, VM: KRviewModel){

    val scrollState = rememberScrollState()

    var isSpoiler by remember { mutableStateOf(false) }

    var ulasan by remember {
        mutableStateOf("")
    }

    val movie by VM.movieDetailsState.collectAsState()

    val currentSession by VM.currentSession.collectAsState()

    var star1 by remember {
        mutableIntStateOf(R.drawable.blank_star)
    }

    var star2 by remember {
        mutableIntStateOf(R.drawable.blank_star)
    }

    var star3 by remember {
        mutableIntStateOf(R.drawable.blank_star)
    }

    var star4 by remember {
        mutableIntStateOf(R.drawable.blank_star)
    }

    var star5 by remember {
        mutableIntStateOf(R.drawable.blank_star)
    }

    var rating by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D36))
            .padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.back),
                contentDescription = "",
                modifier = Modifier
                    .clickable {
                        navController.navigate(MovieDetails)
                    }
            )
            Text(
                text = "Tulis Ulasan Anda",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .width(295.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    movie.title?.let {
                        Text(
                            text = it,
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }

                    movie.releaseYear?.let {
                        Text(
                            text = it.substring(0,4),
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(30.dp)
                )

                Text(
                    text = "Berikan Penilaian Anda",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontSize = 9.sp
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(star1),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                rating = 1
                                when (rating) {
                                    in 1..5 -> {
                                        star1 = R.drawable.star_bigger
                                        star2 = R.drawable.blank_star
                                        star3 = R.drawable.blank_star
                                        star4 = R.drawable.blank_star
                                        star5 = R.drawable.blank_star
                                    }
                                }
                            }
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(star2),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                rating = 2
                                when (rating) {
                                    in 2..5 -> {
                                        star1 = R.drawable.star_bigger
                                        star2 = R.drawable.star_bigger
                                        star3 = R.drawable.blank_star
                                        star4 = R.drawable.blank_star
                                        star5 = R.drawable.blank_star
                                    }
                                }
                            }
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(star3),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                rating = 3
                                when (rating) {
                                    in 3..5 -> {
                                        star1 = R.drawable.star_bigger
                                        star2 = R.drawable.star_bigger
                                        star3 = R.drawable.star_bigger
                                        star4 = R.drawable.blank_star
                                        star5 = R.drawable.blank_star
                                    }
                                }
                            }
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(star4),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                rating = 4
                                when (rating) {
                                    in 4..5 -> {
                                        star1 = R.drawable.star_bigger
                                        star2 = R.drawable.star_bigger
                                        star3 = R.drawable.star_bigger
                                        star4 = R.drawable.star_bigger
                                        star5 = R.drawable.blank_star
                                    }
                                }
                            }
                    )
                    Image(
                        imageVector = ImageVector.vectorResource(star5),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                rating = 5
                                when (rating) {
                                    in 5..5 -> {
                                        star1 = R.drawable.star_bigger
                                        star2 = R.drawable.star_bigger
                                        star3 = R.drawable.star_bigger
                                        star4 = R.drawable.star_bigger
                                        star5 = R.drawable.star_bigger
                                    }
                                }
                            }
                    )

                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isSpoiler,
                        onCheckedChange = { isSpoiler = it }
                    )
                    Text(
                        text = "Mengandung Spoiler",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 9.sp,
                        color = Color.White
                    )
                }
            }

            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500"+movie.poster_Url,
                contentDescription = movie.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .width(150.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        TextField(
            value = ulasan,
            onValueChange = {change ->
                ulasan = change
            },
            textStyle = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 9.sp,
                color = Color.White.copy(alpha = 0.50f)
            ),
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF3D3B54),
                focusedContainerColor = Color(0xFF3D3B54),
                focusedIndicatorColor = Color.Transparent,   // no line when focused
                unfocusedIndicatorColor = Color.Transparent, // no line when not focused
                disabledIndicatorColor = Color.Transparent   // no line when disabled
            ),
            placeholder = {
                Text(
                    text = "Tuliskan ulasan anda...",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color.White.copy(alpha = 0.50f),
                    modifier = Modifier
                )
            },
            modifier = Modifier
                .verticalScroll(scrollState)
                .height(400.dp)
                .fillMaxWidth()
        )


        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Button(
            onClick = {
                movie.movie_Id?.let {
                    currentSession.id?.let { it1 ->
                        VM.postReview(
                            movieId = it,
                            userId = it1,
                            content = ulasan,
                            rating = rating,
                            isSpoiler = isSpoiler
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE9A6A6),
                contentColor = Color.White,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.White
            ),
            modifier = Modifier
                .width(110.dp)
                .height(35.dp)
                .align(Alignment.End)
        ) {
            Text(
                text = "Terbitkan",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = Color(0xFF1F1D36),
            )
        }
    }
}