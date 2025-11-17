package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasihreview.R
import com.example.kasihreview.ui.theme.OpenSans

@Composable
@Preview
fun onBoarding(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1F1D36))
    ) {
        Image(
            painter = painterResource(R.drawable.onboardingimage),
            contentDescription = "foto onboarding",
            modifier = Modifier
                .fillMaxWidth()
                .height(446.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.logohifi),
                contentDescription = "foto logo",
                modifier = Modifier
                    .width(67.dp)
                    .height(68.54.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(70.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Spacer(
                modifier = Modifier
                    .height(35.dp)
            )
            Text(
                text = "Kasih Review",
                fontFamily = OpenSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp,
                color = Color.White,
                modifier = Modifier
            )


        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Spacer(
                modifier = Modifier
                    .height(250.dp)
            )

            Text(
                text = "\"Lacak film yang sudah kamu tonton. Simpan yang ingin kamu lihat. Beri tahu temanmu mana yang bagus\"",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .width(243.dp)
                    .height(110.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE9A6A6),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .width(127.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = "Mulai",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = Color(0xFF1F1D36),
                )
            }
            Spacer(
                modifier = Modifier
                    .height(120.dp)
            )
        }


    }
}