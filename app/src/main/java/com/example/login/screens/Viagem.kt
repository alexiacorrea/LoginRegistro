package com.example.login.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.login.model.Viagem

@Composable
fun Viagem () {

    val list = listOf (
        Viagem (1, "Paris", "05/07/2024", "05/08/2024", 50000.0),
        Viagem (2, "São Paulo", "05/06/2024", "09/06/2024", 3000.0),
        Viagem (3, "Texas", "09/12/2024", "23/12/2024", 18000.0),
    )
    val ctx = LocalContext.current

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(ctx,
                    "Novo",
                    Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    ) {
        Column (modifier = Modifier.padding(it)) {
            LazyColumn(){
                items (items = list){
                    ViagemCard (it)

                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViagemCard (p: Viagem) {
    val ctx = LocalContext.current
    Card (elevation = CardDefaults.cardElevation (
        defaultElevation = 7.dp
    ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Toast
                        .makeText(
                            ctx,
                            "Viagem: ${p.destino}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
                onLongClick = {
                    Toast
                        .makeText(
                            ctx,
                            "Viagem: ${p.destino}",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            )
    ) {

        Column (modifier = Modifier.padding(5.dp)) {
            Text(text = p.destino,
                style = MaterialTheme.typography.titleLarge)
            Text(text = p.dataInicio,
                style = MaterialTheme.typography.bodyMedium)
            Text(text = p.dataFinal,
                style = MaterialTheme.typography.bodyMedium)
            Text(text = "R$ ${p.orcamento}",
                style = MaterialTheme.typography.bodyMedium)

        }

    }
}
