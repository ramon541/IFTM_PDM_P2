package com.iftm.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iftm.ui.theme.White

@Composable
fun TextButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    bgColor: Color,
    fontSize: Int,
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = White
        )
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = fontSize.sp)
    }
}