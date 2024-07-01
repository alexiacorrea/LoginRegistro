package com.example.login.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.login.R
import com.example.login.bd.AppDatabase
import com.example.login.entities.Viagem
import com.example.login.viewmodels.ViagemViewModel
import com.example.login.viewmodels.ViagemViewModelFactory
import java.time.format.DateTimeFormatter

private fun isSelected (currentDestination: NavDestination?, route: String) : Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}
@Composable
fun Viagem (navController: NavController) {

    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)

    val viagemViewModel: ViagemViewModel = viewModel(
        factory = ViagemViewModelFactory(db)
    )

    val viagensItems = viagemViewModel.getAll().collectAsState(initial = emptyList())


    Scaffold (
        floatingActionButton = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            FloatingActionButton(onClick = {
                navController.navigate("formulario")
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    ) {
        Column (modifier = Modifier.padding(it)) {
            LazyColumn(){
                items( items = viagensItems.value) {
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

        Card(elevation = CardDefaults.cardElevation(
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

            Column(modifier = Modifier.padding(5.dp)) {
                Row {
                    if (p.tipo == 0)
                        Image(
                            painter = painterResource(id = R.drawable.lazer),
                            contentDescription = "imagem lazer",
                            modifier = Modifier
                                .size(100.dp)
                        )
                    else
                        Image(
                            painter = painterResource(id = R.drawable.negocio),
                            contentDescription = "imagem negócio",
                            modifier = Modifier
                                .size(100.dp)
                        )


                    var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(
                            text = p.destino,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = p.dataIni.format(formatter),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = p.dataFim.format(formatter),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = p.orcamento.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }
                }
            }


    }
}
