package com.iftm.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iftm.components.ItemCard
import com.iftm.model.Cafe
import com.iftm.ui.theme.Blue
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.iftm.DAO
import com.iftm.components.DataText
import com.iftm.components.TextButton

import com.iftm.ui.theme.White
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCafes(navController: NavController) {
    val context = LocalContext.current

    val listCafes = remember { mutableStateOf<List<Cafe>>(emptyList()) }
    val totCafes = remember { mutableStateOf(0) }
    val precoMedio = remember { mutableStateOf(0.0) }

    val banco: DatabaseReference = Firebase.database.reference
    val dao = DAO(banco)

    //---------------- Functions ----------------
    fun reloadCafes() {
        dao.getData { list ->
            listCafes.value = list
            totCafes.value = list.size

           if(list.size > 0)
               precoMedio.value = listCafes.value.map { it.preco }.average()
            else
                precoMedio.value = 0.0

        }
    }

    reloadCafes()

    //---------------- Component ----------------
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                    "Lista de Cafés",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("salvarCafe")
                },
                contentColor = White,
                containerColor = Blue
            ) {
                Icon(Icons.Filled.Add, "Botão de adicionar Café")
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        "Mini Dashboard",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 10.dp)
                    )
                    DataText("Total de Cafés", totCafes.value.toString())
                    DataText("Preço Médio", "R$ ${String.format("%.2f", precoMedio.value)}")

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(
                            text = "Ordenar por Preço",
                            onClick = {
                                dao.orderByPreco { list ->
                                    listCafes.value = list.asReversed()
                                }
                                Toast.makeText(context, "Ordenado do maior para o menor Preço!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            bgColor = Blue,
                            fontSize = 14,
                        )
                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                        )
                        TextButton(
                            text = "Ordenar por Sabor",
                            onClick = {
                                dao.orderBySabor { list ->
                                    listCafes.value = list.asReversed()
                                }
                                Toast.makeText(context, "Ordenado do maior para o menor Sabor!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            bgColor = Blue,
                            fontSize = 14,
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TextButton(
                            text = "Ordenar por Aroma",
                            onClick = {
                                dao.orderByAroma { list ->
                                    listCafes.value = list.asReversed()
                                }
                                Toast.makeText(context, "Ordenado do maior para o menor Aroma!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            bgColor = Blue,
                            fontSize = 14,
                        )
                        Spacer(
                            modifier = Modifier
                                .width(10.dp)
                        )
                        TextButton(
                            text = "Ordenar por Acidez",
                            onClick = {
                                dao.orderByAcidez { list ->
                                    listCafes.value = list
                                }
                                Toast.makeText(context, "Ordenado do menor para a maior Acidez!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            bgColor = Blue,
                            fontSize = 14,
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                    )
                    TextButton(
                        text = "Limpar filtros",
                        onClick = {
                            reloadCafes()
                            Toast.makeText(context, "Filtragem original!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        bgColor = Blue,
                        fontSize = 14,
                    )
                }
            }

            Text(
                text = "Cafés:",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
            )

            if (listCafes.value.isEmpty()) {
                Text(
                    text = "Nenhum café cadastrado.",
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            } else {
                for (cafe in listCafes.value) {
                    ItemCard(
                        cafe,
                        onEdit = {
                            val cafeJson = Json.encodeToString(cafe)
                            navController.navigate("editarCafe/$cafeJson")
                        },
                        onDelete = {
                            dao.delete(cafe.codigo)
                            Toast.makeText(context, "Café ${cafe.nome} deletado!", Toast.LENGTH_SHORT).show()
                            reloadCafes()
                        }
                    )
                }
            }
        }
    }
}