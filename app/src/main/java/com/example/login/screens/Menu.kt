package com.example.login.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.login.bd.AppDatabase
import com.example.login.isSelected
import com.example.login.viewmodels.UsuarioViewModel
import com.example.login.viewmodels.UsuarioViewModelFactory

private fun isSelected (currentDestination: NavDestination?, route: String) : Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

@Composable
fun Menu() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            BottomNavigation {
                BottomNavigationItem(
                    selected = isSelected(currentDestination, "home"),
                    onClick = { navController.navigate("home") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "viagem"),
                    onClick = { navController.navigate("viagem") },
                    icon = {
                        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "sobre"),
                    onClick = { navController.navigate("sobre") },
                    icon = {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                    })
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

            NavHost(navController = navController, startDestination = "home" ) {
                composable("home") {
                    Home()
                }
                composable("viagem") {
                    Viagem()
                }
                composable("sobre") {
                    Sobre()
                }

            }


        }
    }
}

@Composable
fun Home() {

    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)
    val usuarioViewModel: UsuarioViewModel = viewModel(
        factory = UsuarioViewModelFactory(db)
    )
    Column {
        Text(
            text = "HOME",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )

        val productItems = usuarioViewModel.getAll().collectAsState(initial = emptyList())
        LazyColumn() {
            items(items = productItems.value) {
                Card {
                    Text(text = it.user)
                    Text(text = it.email)
                }
            }
        }
    }
}



/*@Preview(showBackground = true)
@Composable
fun PreviewMenu(){
    Menu()
}
}
*\
 */
