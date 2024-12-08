package com.iftm.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DataText(
    data: String,
    text: String
) {
    Row(
        modifier = Modifier
            .padding(0.dp, 5.dp)
    ) {
        Text(text = "${data}:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        Text(text = text, fontSize = 16.sp)
    }
}
