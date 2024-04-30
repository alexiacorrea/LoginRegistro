package com.example.login.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.login.R

@Composable
fun Login(onLogin: () -> Unit,
          onRegistro: () -> Unit) {
    val context = LocalContext.current

    val usuario = remember {
        mutableStateOf("")
    }

    val senha = remember {
        mutableStateOf("")
    }

    val senhaVisivel = remember {
        mutableStateOf(false)
    }

    val navController = rememberNavController()

    Column (modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "imagem login",
            modifier = Modifier
                .size(360.dp)
        )

        Text(
            text = "Boa Viagem",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        )

        Text(
            text = "Usuário",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = usuario.value,
            onValueChange = { usuario.value = it },
            label = {
                Text(text = "Usuário")
            }
        )

        Text(
            text = "Senha",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = senha.value,
            onValueChange = { senha.value = it },
            label = {
                Text(text = "Senha")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation =
            if (senhaVisivel.value)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    senhaVisivel.value = !senhaVisivel.value
                }) {
                    if (senhaVisivel.value)
                        Icon(
                            painterResource(id = R.drawable.visivel), ""
                        )
                    else
                        Icon(
                            painterResource(id = R.drawable.olho), ""
                        )
                }
            }
        )
        Button(onClick = {
            if (usuario.value == "admin" && senha.value == "admin") {
                onLogin()
            } else {
                context.toast("Usuário ou senha incorreto")
            }
        }) {
            Text(
                text = "Entrar",
                fontSize = 18.sp
            )
        }

        Button(onClick = { onRegistro() }) {
            Text(text = "Novo Usuário")
        }
    }
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    Login(onLogin = {}, onRegistro = {})
}

