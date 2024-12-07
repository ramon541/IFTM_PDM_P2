package com.iftm.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iftm.components.DropdownMenu
import com.iftm.components.InputText
import com.iftm.components.TextButton
import com.iftm.ui.theme.DarkBlue
import com.iftm.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SalvarCafe(navController: NavController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Salvar Café",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = White,
                            contentDescription = "Localized description"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBlue,
                    titleContentColor = White
                )
            )
        }
    ) { contentPadding ->
        val notas = listOf("Doce", "Floral", "Frutado", "Especiarias")
        val avaliacoes = listOf("1", "2", "3", "4", "5")

        var nome    by remember { mutableStateOf("") }
        var nota    by remember { mutableStateOf(notas[0]) }
        var aroma   by remember { mutableStateOf(avaliacoes[4]) }
        var acidez  by remember { mutableStateOf(avaliacoes[4]) }
        var amargor by remember { mutableStateOf(avaliacoes[4]) }
        var sabor   by remember { mutableStateOf(avaliacoes[4]) }
        var preco   by remember { mutableStateOf("") }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            InputText(
                value = nome,
                onValueChange = {
                    nome = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp),
                label = "Nome",
                maxLines = 1
            )

            DropdownMenu(
                label = "Nota:",
                items = notas,
                selectedItem = nota,
                onItemSelected = {
                    nota = it
                }
            )

            DropdownMenu(
                label = "Aroma:",
                items = avaliacoes,
                selectedItem = aroma,
                onItemSelected = {
                    aroma = it
                }
            )

            DropdownMenu(
                label = "Acidez:",
                items = avaliacoes,
                selectedItem = acidez,
                onItemSelected = {
                    acidez = it
                }
            )

            DropdownMenu(
                label = "Amargor:",
                items = avaliacoes,
                selectedItem = amargor,
                onItemSelected = {
                    amargor = it
                }
            )

            DropdownMenu(
                label = "Sabor:",
                items = avaliacoes,
                selectedItem = sabor,
                onItemSelected = {
                    sabor = it
                }
            )

            InputText(
                value = preco,
                onValueChange = {
                    preco = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp),
                label = "Preço",
                maxLines = 1,
                keyboardType = KeyboardType.Number
            )

            TextButton(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        
                    }

                },
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                text = "Salvar"
            )
        }
    }
}