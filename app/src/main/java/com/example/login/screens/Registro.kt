package com.example.login.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.login.R

@Composable
fun Registro(onBack: () -> Unit) {
    var usuario = remember {
        mutableStateOf("")
    }

    var senha = remember {
        mutableStateOf("")
    }

    var email = remember {
        mutableStateOf("")
    }

    var senhaVisivel = remember {
        mutableStateOf(false)
    }

    Column (modifier = Modifier.fillMaxSize()){

        Text(
            text = "Registro",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
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

        Text(
            text = "E-mail",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = {
                Text(text = "E-mail")
            }
        )

        Button(onClick = { onBack() }) {
            Text(text = "Registrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu(){
    Registro(onBack = {})
}

