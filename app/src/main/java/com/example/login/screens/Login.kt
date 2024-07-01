package com.example.login.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login.R
import com.example.login.bd.AppDatabase
import com.example.login.viewmodels.UsuarioViewModel
import com.example.login.viewmodels.UsuarioViewModelFactory

@Composable
fun Login(
    onLogin: () -> Unit,
    onRegistro: () -> Unit
) {
    val context = LocalContext.current

    val senhaVisivel = remember { mutableStateOf(false) }
    val user = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }

    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)

    val usuarioViewModel: UsuarioViewModel = viewModel(
        factory = UsuarioViewModelFactory(db)
    )

    val userState = usuarioViewModel.getUserByCredentials(user.value, senha.value).collectAsState(initial = null)

    Column(modifier = Modifier.fillMaxSize()) {
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
            value = user.value,
            onValueChange = { user.value = it },
            label = { Text(text = "Usuário") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Senha",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = senha.value,
            onValueChange = { senha.value = it },
            label = { Text(text = "Senha") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (senhaVisivel.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { senhaVisivel.value = !senhaVisivel.value }) {
                    if (senhaVisivel.value)
                        Icon(painterResource(id = R.drawable.visivel), contentDescription = "")
                    else
                        Icon(painterResource(id = R.drawable.olho), contentDescription = "")
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val usuario = userState.value
                    if (usuario != null) {
                        onLogin()
                    } else {
                        context.toast("Usuário ou senha incorreto")
                    }
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Entrar",
                    fontSize = 18.sp
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onRegistro() },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(text = "Novo Usuário")
            }
        }
    }
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    Login(onLogin = {}, onRegistro = {})
}
