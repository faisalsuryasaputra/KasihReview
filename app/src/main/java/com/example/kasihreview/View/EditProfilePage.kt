package com.example.kasihreview.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kasihreview.NavObjects.profilePage
import com.example.kasihreview.ViewModel.KRviewModel
import com.example.kasihreview.ui.theme.OpenSans
import kotlinx.coroutines.launch

@Composable
fun editProfilePage(navController: NavController, VM: KRviewModel){

    val scope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    val account by VM.currentSession.collectAsState()

    var bio by remember {
        mutableStateOf(account.bio)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1D36))
            .padding(15.dp)
    ) {
        bio?.let {
            TextField(
                value = it,
                onValueChange = {change ->
                    bio = change
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
                        text = "Tuliskan Bio anda...",
                        fontFamily = OpenSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 9.sp,
                        color = Color.White.copy(alpha = 0.50f),
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1f)
                    .fillMaxWidth()
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Button(
                onClick = {
                    scope.launch {
                        VM.updateUserProfile(account.copy(bio = bio))
                        account.id?.let { VM.getMovieGoerById(it) }
                        navController.navigate(profilePage)
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE9A6A6),
                    contentColor = Color.White,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                ),
                modifier = Modifier
//                .width(110.dp)
//                .height(35.dp)
            ) {
                Text(
                    text = "Perbarui",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = Color(0xFF1F1D36),
                )
            }
        }


    }

}