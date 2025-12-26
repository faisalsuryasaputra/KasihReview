//package com.example.kasihreview.View
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.vectorResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.kasihreview.NavObjects.EditProfilePage
//import com.example.kasihreview.NavObjects.EditUlasanPage
//import com.example.kasihreview.NavObjects.UlasanDetail
//import com.example.kasihreview.NavObjects.WatchListPage
//import com.example.kasihreview.R
//import com.example.kasihreview.ViewModel.KRviewModel
//import com.example.kasihreview.ui.theme.OpenSans
//
//@Composable
//fun viewOtherUser(navController: NavController,VM: KRviewModel){
//
//    val accountReviews by VM.accountReviews.collectAsState()
//
//
//    LaunchedEffect(Unit) {
//        account.id?.let { VM.getReviewByMovieGoerId(it) }
//        account.id?.let { VM.getMovieGoerById(it) }
//        account.id?.let { VM.getWatchListByUserId(it) }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF1F1D36))
//    ) {
//
//        /** -------- BACKGROUND IMAGE FIXED BEHIND CONTENT -------- */
//        Image(
//            painter = painterResource(R.drawable.bgprofile),
//            contentDescription = "header",
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.TopCenter)
//        )
//
//
//
//        /** -------- SCROLL AREA CONTENT (LAZYCOLUMN) -------- */
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 10.dp),  // supaya konten turun sedikit dari gambar
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top,
//            contentPadding = PaddingValues(bottom = 30.dp, start = 10.dp, end = 10.dp)
//        ) {
//
//
//            /** LOGO */
//            item {
//                Image(
//                    painter = painterResource(R.drawable.logohifi),
//                    contentDescription = "",
//                    modifier = Modifier.size(60.dp)
//                )
//
//                Spacer(Modifier.height(5.dp))
//
//                Text(
//                    text = "Kasih Review",
//                    fontFamily = OpenSans,
//                    fontWeight = FontWeight.ExtraBold,
//                    fontSize = 17.sp,
//                    color = Color.White
//                )
//
//                Spacer(Modifier.height(20.dp))
//            }
//
//
//            /** PROFILE PICTURE + USERNAME */
//            item {
//                Image(
//                    imageVector = ImageVector.vectorResource(R.drawable.genericavatar),
//                    contentDescription = "pp",
//                    modifier = Modifier
//                        .size(78.dp)
//                        .clip(CircleShape)
//                )
//
//                account.username?.let {
//                    Text(
//                        text = it,
//                        fontFamily = OpenSans,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 18.sp,
//                        color = Color.White
//                    )
//                }
//
//                Spacer(Modifier.height(15.dp))
//            }
//
//
//            /** BIO */
//            item {
//                account.bio?.let {
//                    Text(
//                        text = it,
//                        fontFamily = OpenSans,
//                        fontWeight = FontWeight.Normal,
//                        fontSize = 8.sp,
//                        color = Color.White,
//                        lineHeight = 12.sp,
//                        modifier = Modifier.width(150.dp)
//                    )
//                }
//
//                Spacer(Modifier.height(15.dp))
//            }
//
//            /** COUNT SECTION */
//            item {
//                Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text(accountWatchList.movies.size.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE9A6A6))
//                        Text("Saved", fontSize = 12.sp, color = Color.White)
//                    }
//
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Text(accountReviews.allReviews.size.toString(), fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF9C4A8B))
//                        Text("Ulasan", fontSize = 12.sp, color = Color.White)
//                    }
//                }
//
//                Spacer(Modifier.height(20.dp))
//            }
//
//
//            /** ULASAN TITLE */
//            item {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ){
//                    Text(
//                        text = "Terakhir Diriview",
//                        fontFamily = OpenSans,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 10.sp,
//                        color = Color.White,
//                        modifier = Modifier
//                    )
//                }
//                Spacer(Modifier.height(10.dp))
//            }
//
//            /** ULASAN LIST */
//
//            /** ULASAN LIST */
//
//            /** ULASAN LIST */
//
//            /** ULASAN LIST */
//            items(accountReviews.allReviews) { review ->
//                ulasanPrev(
//                    review,
//                    Modifier.clickable {
//                        VM.getReviewById(review.reviewId)
//                        VM.getMovieDetailsById(review.movieId)
//                        if (review.reviewerName == account.username) {
//                            navController.navigate(EditUlasanPage)
//                        }else {
//                            navController.navigate(UlasanDetail)
//                        }
//                    },
//                    VM
//                )
//                Spacer(Modifier.height(10.dp))
//            }
//        }
//    }
//}