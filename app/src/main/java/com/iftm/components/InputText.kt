package com.iftm.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.iftm.ui.theme.Blue
import com.iftm.ui.theme.Gray
import com.iftm.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputText(
    value: Any,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange,
        modifier,
        label = { Text(text = label) },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Blue,
            unfocusedTextColor = LightBlue,
            focusedBorderColor = Blue,
            unfocusedBorderColor = LightBlue,
            focusedLabelColor = Blue,
            unfocusedLabelColor = LightBlue,
            cursorColor = Blue,

            disabledTextColor = Gray,
            disabledBorderColor = Gray,
            disabledLabelColor = Gray,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = { },
            onGo = { },
            onNext = { },
            onPrevious = { },
            onSearch = { },
            onSend = { }
        ),
        enabled = enabled,
    )
}
