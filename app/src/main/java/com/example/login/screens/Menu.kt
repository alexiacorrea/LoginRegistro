package com.example.login.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.login.isSelected

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
                    selected = isSelected(currentDestination, "menu"),
                    onClick = { navController.navigate("menu") },
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

            Text(
                text = "Menu",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    class SegundaAtividade : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                LoginTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Usuario()
                    }
                }
            }
        }
    }

    @Composable
    fun Usuario() {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Usuário: Alexia Correa",
                fontSize = 22.sp,
            )
        }
    }
    class SegundaAtividade : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                LoginTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Usuario()
                    }
                }
            }
        }
    }

    @Composable
    fun Usuario() {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Usuário: Alexia Correa",
                fontSize = 22.sp,
            )
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

