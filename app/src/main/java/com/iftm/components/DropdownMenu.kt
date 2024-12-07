package com.iftm.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf(items.indexOf(selectedItem)) }

    Text(text = label, Modifier.padding(20.dp, 20.dp, 20.dp, 5.dp))
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 0.dp, 20.dp, 10.dp),
    ) {
        TextField(
            value = items[selectedItemIndex],
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            fontWeight = if (index == selectedItemIndex) FontWeight.Bold else null
                        )
                    },
                    onClick = {
                        selectedItemIndex = index
                        expanded = false
                        onItemSelected(item) // Notifica o item selecionado
                    }
                )
            }
        }
    }
}
