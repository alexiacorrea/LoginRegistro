package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoaViagem() {

    val navController = rememberNavController()

    Scaffold (
        topBar = {
            TopAppBar(title = {
                Text(text = "Room Database")
            })
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
                    })}


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























