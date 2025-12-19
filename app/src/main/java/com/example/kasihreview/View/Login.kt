package com.example.kasihreview.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kasihreview.NavObjects.HomePage
import com.example.kasihreview.NavObjects.daftarPage
import com.example.kasihreview.R
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans

@SuppressLint("InvalidColorHexValue")
@Composable
fun loginPage(navController: NavController, viewModel: KRviewModel){

    var userNameInput by remember {
        mutableStateOf("")
    }

    var userPwInput by remember {
        mutableStateOf("")
    }

    var loginError by remember {
        mutableStateOf("")
    }

    val account by viewModel.currentSession.collectAsState()

    val serverError by viewModel.serverErrorMessage.collectAsState()

    LaunchedEffect(serverError) {
        if (serverError == "")
        navController.navigate(HomePage)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF1F1D36))
    ) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "foto onboarding",
            modifier = Modifier
                .fillMaxWidth()
                .height(389.dp)
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
                    .height(250.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {

            Text(
                text = "Kasih Review",
                fontFamily = OpenSans,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp,
                color = Color.White,
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier
                    .height(145.dp)
            )


        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Spacer(
                modifier = Modifier
                    .height(275.dp)
            )
            Text(
                text = "Masuk",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
            )

            Text(
                text = "Masuk Untuk Melanjutkan",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            TextField(
                value = userNameInput,
                onValueChange = { change ->
                    userNameInput = change
                },
                textStyle = TextStyle(
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color.White.copy(alpha = 0.50f)
                ),
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFC4C4C4).copy(alpha = 0.35f),
                    focusedContainerColor = Color(0xFFC4C4C4).copy(alpha = 0.35f),
                    focusedIndicatorColor = Color.Transparent,   // no line when focused
                    unfocusedIndicatorColor = Color.Transparent, // no line when not focused
                    disabledIndicatorColor = Color.Transparent   // no line when disabled
                ),
                placeholder = {
                    Text(
                        text = "Username",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 9.sp,
                        color = Color.White.copy(alpha = 0.50f),
                        modifier = Modifier
                    )
                },
                leadingIcon = {

                    Image(imageVector = ImageVector.vectorResource(R.drawable.union),
                        contentDescription = "",
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp))
                },
                modifier = Modifier
                    .width(243.dp)
                    .height(56.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            TextField(
                value = userPwInput,
                onValueChange = { change ->
                    userPwInput = change
                },
                textStyle = TextStyle(
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color.White.copy(alpha = 0.50f)
                ),
                shape = RoundedCornerShape(30.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFC4C4C4).copy(alpha = 0.35f),
                    focusedContainerColor = Color(0xFFC4C4C4).copy(alpha = 0.35f),
                    focusedIndicatorColor = Color.Transparent,   // no line when focused
                    unfocusedIndicatorColor = Color.Transparent, // no line when not focused
                    disabledIndicatorColor = Color.Transparent   // no line when disabled
                ),
                placeholder = {
                    Text(
                        text = "Password",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 9.sp,
                        color = Color.White.copy(alpha = 0.50f),
                        modifier = Modifier
                    )
                },
                leadingIcon = {

                    Image(imageVector = ImageVector.vectorResource(R.drawable.pw),
                        contentDescription = "",
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp))
                },
                modifier = Modifier
                    .width(243.dp)
                    .height(56.dp)
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Text(
                text = "Lupa Password?",
                fontFamily = OpenSans,
                fontWeight = FontWeight.Normal,
                fontSize = 9.sp,
                color = Color(0xFFE9A6A6),
                modifier = Modifier
                    .align(Alignment.End)
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Button(
                onClick = {
                    viewModel.loginAuthentication(userNameInput, userPwInput)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE9A6A6),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
                    .width(95.dp)
                    .height(35.dp)
            ) {
                Text(
                    text = "Masuk",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1F1D36),
                )
            }

            Text(
                text = serverError,
                fontFamily = OpenSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(
                    text = "Belum punya akun? Silahkan ",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color(0xFFE9A6A6),
                    )

                Text(
                    text = "Daftar ",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color(0xFF9C4A8B),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(daftarPage)
                        }
                )

                Text(
                    text = "terlebih dahulu",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    color = Color(0xFFE9A6A6),
                )
            }

        }




    }
}