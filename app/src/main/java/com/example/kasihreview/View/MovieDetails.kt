package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.example.kasihreview.NavObjects.EditUlasanPage
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.UlasanDetail
import com.example.kasihreview.NavObjects.UlasanPage
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans
import kotlinx.coroutines.launch

@Composable
fun movieDetails(navController: NavController, viewModel: KRviewModel){
    var rating = 4.5

    val movie by viewModel.movieDetailsState.collectAsState()

    val reviews by viewModel.movieReviews.collectAsState()

    val account by viewModel.currentSession.collectAsState()

    movie.movie_Id?.let { viewModel.getReviewByMovieId(it) }

    var spoiler by remember { mutableStateOf(false) }

    var inList by remember { mutableStateOf("Tambah Ke List") }

    account.id?.let { viewModel.getReviewByMovieGoerId(it) }



    val accountWatchList by viewModel.accountWatchList.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        account.id?.let { viewModel.getWatchListByUserId(it) }
        for (i in accountWatchList.movies) {
            if (i.movieId == movie.movie_Id) {
                inList = "Hapus Dari List"
                break
            }else {
                inList = "Tambah Ke List"
            }
        }
    }



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

                Button(
                    onClick = {
                        navController.navigate(UlasanPage)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9A6A6),
                        contentColor = Color.White,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(170.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ulas),
                        contentDescription = "",
                    )

                    Text(
                        text = "Nilai atau Ulas",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }

                Button(
                    onClick = {
                        if (inList == "Hapus Dari List") {
                            inList = "Tambah Ke List"
                            println(inList)
                            scope.launch {
                                account.id?.let { movie.movie_Id?.let { it1 ->
                                    viewModel.deleteMovieFromWatchList(it,
                                        it1
                                    )
                                } }
                            }
                        }else if (inList == "Tambah Ke List") {
                            inList = "Hapus Dari List"
                            println(inList)
                            scope.launch {
                                account.id?.let { accountId ->
                                    movie.movie_Id?.let { movieId ->
                                        viewModel.postMovieToWatchList(accountId, movieId)
                                    }
                                }
                            }
                        }

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9A6A6),
                        contentColor = Color.White,
                        disabledContainerColor = Color.White,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(170.dp)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ulas),
                        contentDescription = "",
                    )



                    Text(
                        text = inList,
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                }

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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(80.dp)
        ){
            Text(
                text = "Semua Ulasan",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Button(
                    onClick = {
                        spoiler = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9A6A6),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(35.dp)
                        .width(108.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE9A6A6),
                            shape = RoundedCornerShape(17.5.dp)
                        )
                        .clip(RoundedCornerShape(17.5.dp)), // penting
                    shape = RoundedCornerShape(17.5.dp),
                    contentPadding = PaddingValues(0.dp) // hilangin gap
                ) {
                    Text(
                        text = "Non-Spoiler",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Black,
                    )
                }

                Button(
                    onClick = {
                        spoiler = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE9A6A6),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .height(35.dp)
                        .width(108.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE9A6A6),
                            shape = RoundedCornerShape(17.5.dp)
                        )
                        .clip(RoundedCornerShape(17.5.dp)), // penting
                    shape = RoundedCornerShape(17.5.dp),
                    contentPadding = PaddingValues(0.dp) // hilangin gap
                ) {
                    Text(
                        text = "Spoiler",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Black,
                    )
                }
            }



        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(reviews.allReviews) {review ->
                if (!spoiler && !review.isSpoiler) {
                    ulasanPrev(
                        review,
                        Modifier.clickable {
                            viewModel.getReviewById(review.reviewId)
                            viewModel.getMovieDetailsById(review.movieId)
                            if (review.reviewerName == account.username) {
                                navController.navigate(EditUlasanPage)
                            }else {
                                navController.navigate(UlasanDetail)
                            }
                        },
                        viewModel
                    )
                }else if (spoiler && review.isSpoiler) {
                    ulasanPrev(
                        review,
                        Modifier.clickable {
                            viewModel.getReviewById(review.reviewId)
                            viewModel.getMovieDetailsById(review.movieId)
                            if (review.reviewerName == account.username) {
                                navController.navigate(EditUlasanPage)
                            }else {
                                navController.navigate(UlasanDetail)
                            }
                        },
                        viewModel
                    )
                }
            }
        }

    }
}