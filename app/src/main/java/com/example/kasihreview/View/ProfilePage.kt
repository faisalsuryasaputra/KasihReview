package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasihreview.R
import com.example.kasihreview.ui.theme.OpenSans


@Preview
@Composable
fun profilePage(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1F1D36))
    ) {
        Image(
            painter = painterResource(R.drawable.bgprofile),
            contentDescription = "header",
            modifier = Modifier

        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Image(imageVector = ImageVector.vectorResource(R.drawable.logohifi),
                contentDescription = "",
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )
            Text(
                text = "Kasih Review",
                fontFamily = OpenSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 17.sp,
                color = Color.White,
                modifier = Modifier
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Image(
                painter = painterResource(R.drawable.ella),
                contentDescription = "header",
                modifier = Modifier
                    .width(78.dp)
                    .height(78.dp)
                    .clip(CircleShape)

            )

            Text(
                text = "Ella",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
            )

            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )


            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "4",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFFE9A6A6),
                        modifier = Modifier
                    )
                    Text(
                        text = "Saved",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "30",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color(0xFF9C4A8B),
                        modifier = Modifier
                    )
                    Text(
                        text = "Ulasan",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Text(
                text = "Tonton Nanti",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .width(300.dp)
            ) {
                items(10) {
                    Image(
                        painter = painterResource(R.drawable.fightclub),
                        contentDescription = "",
                        modifier = Modifier
                            .height(100.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White.copy(0.19f))
            )

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(240.dp)
            ){
                Text(
                    text = "Terakhir Diriview",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier
                )

                Text(
                    text = "Lihat Semua",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    color = Color(0xFFE9A6A6),
                    modifier = Modifier
                )

            }

            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )

            var rating = 4.5

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
            ) {
                items(5) {
                    Column {
                        Image(
                            painter = painterResource(R.drawable.fightclub),
                            contentDescription = "",
                            modifier = Modifier
                                .height(100.dp)
                                .clip(RoundedCornerShape(5.dp))
                        )

                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
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

                        Spacer(
                            modifier = Modifier
                                .height(5.dp)
                        )

                        Text(
                            text = "Baca Ulasan >",
                            fontFamily = OpenSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 6.sp,
                            color = Color(0xFF9C4A8B),
                            modifier = Modifier
                        )
                    }


                }

            }

            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(240.dp)
            ){
                Text(
                    text = "Ulasan Terakhir",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier
                )

                Text(
                    text = "Lihat Semua",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 9.sp,
                    color = Color(0xFFE9A6A6),
                    modifier = Modifier
                )

            }

        }




    }
}