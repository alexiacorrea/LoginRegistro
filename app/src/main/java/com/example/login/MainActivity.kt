package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.login.screens.Login
import com.example.login.screens.Menu
import com.example.login.screens.Registro
import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BoaViagem()
                }
            }
        }
    }
}

fun isSelected (currentDestination: NavDestination?, route: String) : Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}

@Composable
fun BoaViagem() {

    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            BottomNavigation {
                BottomNavigationItem(
                    selected = isSelected(currentDestination,"profile"),
                    onClick = { navController.navigate("profile")},
                    icon = {
                        Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination,"viagem"),
                    onClick = { navController.navigate("viagem")},
                    icon = {
                        Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                    })

                BottomNavigationItem(
                    selected = isSelected(currentDestination,"sobre"),
                    onClick = { navController.navigate("sobre")},
                    icon = {
                        Icon(imageVector = Icons.Filled.Info, contentDescription = "")
                    })
            }
        }
    ){
        Column (modifier = Modifier.padding(it)){

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    Login(onLogin = {
                        navController.navigate("menu")},
                        onRegistro = {
                        navController.navigate("registro")
                    })
                }
                composable("menu") {
                    Menu()
                }
                composable("registro") {
                    Registro( onBack = {
                        navController.navigate("login")
                    })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoaViagemPreview() {
    LoginTheme {
        BoaViagem()
    }
}























