package com.iftm.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iftm.components.ItemCard
import com.iftm.model.Cafe
import com.iftm.ui.theme.Blue
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.iftm.DAO

import com.iftm.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCafes(navController: NavController) {
    var listCafes = remember { mutableStateOf<List<Cafe>>(emptyList()) }

    val banco: DatabaseReference = Firebase.database.reference
    val dao = DAO(banco)

    //---------------- Functions ----------------
    fun reloadCafes() {
        dao.getData { list ->
            listCafes.value = list
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
            for (cafe in listCafes.value) {
                ItemCard(cafe)
            }
        }
    }
}