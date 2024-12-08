package com.iftm.view

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.iftm.DAO
import com.iftm.components.DropdownMenu
import com.iftm.components.InputText
import com.iftm.components.TextButton
import com.iftm.model.Cafe
import com.iftm.ui.theme.DarkBlue
import com.iftm.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditarCafe(navController: NavController, cafe: Cafe) {
    val context = LocalContext.current

    val banco: DatabaseReference = Firebase.database.reference
    val dao = DAO(banco)

    val notas      = listOf("Doce", "Floral", "Frutado", "Especiarias")
    val avaliacoes = listOf("1", "2", "3", "4", "5")

    var codigo  by remember { mutableStateOf(cafe.codigo) }
    var nome    by remember { mutableStateOf(cafe.nome) }
    var nota    by remember { mutableStateOf(notas[notas.indexOf(cafe.nota)]) }
    var aroma   by remember { mutableStateOf(avaliacoes[avaliacoes.indexOf(cafe.aroma.toString())]) }
    var acidez  by remember { mutableStateOf(avaliacoes[avaliacoes.indexOf(cafe.acidez.toString())]) }
    var amargor by remember { mutableStateOf(avaliacoes[avaliacoes.indexOf(cafe.amargor.toString())]) }
    var sabor   by remember { mutableStateOf(avaliacoes[avaliacoes.indexOf(cafe.sabor.toString())]) }
    var preco   by remember { mutableStateOf(cafe.preco.toString()) }

    //---------------- Component ----------------

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Editar Café",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            InputText(
                value = codigo,
                onValueChange = {
                    codigo = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp),
                label = "Código",
                maxLines = 1,
                enabled = false
            )

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
                    val cafe = Cafe(codigo.toString(), nome.toString(), nota, aroma.toInt(), acidez.toInt(), amargor.toInt(), sabor.toInt(), preco.toDouble())
                    dao.saveOrUpdate(cafe)

                    Toast.makeText(context, "Café editado com sucesso!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                text = "Salvar",
                bgColor = DarkBlue,
                fontSize = 14
            )
        }
    }
}