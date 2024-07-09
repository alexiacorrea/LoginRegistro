package com.example.login.screens

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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.login.R
import com.example.login.bd.AppDatabase
import com.example.login.entities.Viagem
import com.example.login.viewmodels.ViagemViewModel
import com.example.login.viewmodels.ViagemViewModelFactory
import java.time.format.DateTimeFormatter

@Composable
fun dest(){

}
private fun isSelected (currentDestination: NavDestination?, route: String) : Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}
@Composable
fun Viagem () {

    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)

    val viagemViewModel: ViagemViewModel = viewModel(
        factory = ViagemViewModelFactory(db)
    )

    val navController = rememberNavController()

    val viagensItems = viagemViewModel.getAll().collectAsState(initial = emptyList())


    Scaffold (
        floatingActionButton = {

            FloatingActionButton(onClick = {
                navController.navigate("formulario/${-1L}")
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    ) {


        Column (modifier = Modifier.padding(it)) {

            NavHost(
                navController = navController,
                startDestination = "dest"){

                composable("dest"){
                    dest()
                }

                composable("formulario/{viagemId}", arguments = listOf(navArgument("viagemId"){
                    type = NavType.LongType ; defaultValue = -1L})){
                    entry -> entry.arguments?.getLong("viagemId").let { it
                        Formulario(
                            onBack = {navController.navigateUp()}, it
                        )
                    }
                }
            }

            LazyColumn(){

                items( items = viagensItems.value) {
                    ViagemCard (
                        p = it,
                        onDelet = {
                            viagemViewModel.delet(it)
                        },
                        onEdit = {
                            navController.navigate("formulario/${it.id}")
                        }
                    )


                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViagemCard (p: Viagem, onDelet : () -> Unit, onEdit : () -> Unit) {

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

                        onEdit()
//                        Toast
//                            .makeText(
//                                ctx,
//                                "Viagem: ${p.destino}",
//                                Toast.LENGTH_SHORT
//                            )
//                            .show()
                    },
                    onLongClick = {

                        onDelet()

//                        Toast
//                            .makeText(
//                                ctx,
//                                "Viagem: ${p.destino}",
//                                Toast.LENGTH_SHORT
//                            )
//                            .show()
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
