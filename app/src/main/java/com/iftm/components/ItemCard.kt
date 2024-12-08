package com.iftm.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iftm.model.Cafe
import com.iftm.ui.theme.White

@Composable
fun ItemCard(
    cafe: Cafe
) {
    Card (
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
               .fillMaxWidth()
               .padding(10.dp)
        ) {
            Text(text = cafe.nome, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 15.dp),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    DataText("Código", cafe.codigo)
                    DataText("Nota", cafe.nota)
                    DataText("Aroma", cafe.aroma.toString())
                    DataText("Acidez", cafe.acidez.toString())
                }
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    DataText("Amargor", cafe.amargor.toString())
                    DataText("Sabor", cafe.sabor.toString())
                    DataText("Preço", "R$ ${cafe.preco}")
                }
            }
        }

    }

}