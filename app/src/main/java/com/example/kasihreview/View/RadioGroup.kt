package com.example.kasihreview.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kasihreview.ui.theme.OpenSans

@Composable
fun radioGroup(){
    var radioLabels = listOf<String>("Name", "Year", "Rating")

    var seleceted by remember {
        mutableStateOf(radioLabels[0])
    }

    var selecetedForOrder by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        radioLabels.forEach { radio ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { seleceted = radio }
            ) {
                RadioButton(
                    selected = seleceted == radio,
                    onClick = {
                        seleceted = radio
                    }
                )

                Text(
                    text = radio,
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }

        Text(
            text = "IN",
            fontFamily = OpenSans,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .padding(start = 15.dp)
        )

        Row {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { selecetedForOrder = "Asc" }
            ) {
                RadioButton(
                    selected = selecetedForOrder == "Asc",
                    onClick = {
                        selecetedForOrder = "Asc"
                    }
                )

                Text(
                    text = "Ascending",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { selecetedForOrder = "Desc" }
            ) {
                RadioButton(
                    selected = selecetedForOrder == "Desc",
                    onClick = {
                        selecetedForOrder = "Desc"
                    }
                )

                Text(
                    text = "Descending",
                    fontFamily = OpenSans,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}