package com.example.kasihreview.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasihreview.R
import com.example.kasihreview.ui.theme.OpenSans

var review = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In congue semper pharetra. Pellentesque euismod justo eget rhoncus fringilla. Phasellus pharetra magna id ante accumsan rutrum. Nunc pretium ex nibh, nec faucibus leo ornare nec. Proin accumsan mi mi, eget ornare felis accumsan maximus. Duis nec pharetra risus. Nam euismod nisi eget justo efficitur interdum. Nulla cursus ornare condimentum. Aliquam porta dolor sit amet aliquam euismod. Aliquam sodales, augue nec lobortis eleifend, mi libero blandit neque,"


@Composable
fun ulasanPrev(){
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFFE9A6A6).copy(0.05f))
            .height(180.dp)
            .padding(5.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.ella), contentDescription = "bidadari cantik"
                , modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(CircleShape))

            Text(
                text = "Ulasan dari",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color.White.copy(0.5f)
            )

            Spacer(modifier = Modifier
                .width(5.dp))

            Text(
                text = "Ella",
                fontFamily = OpenSans,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFE9A6A6)
            )
        }

        if (review.length > 159) {

            review = "${review.substring(0,159)}..."
        }

        Text(
            text = review,
            fontFamily = OpenSans,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )

        Text(
            text = "Baca Selengkapnya >",
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = Color(0xFF9C4A8B),
            modifier = Modifier
        )
    }
}