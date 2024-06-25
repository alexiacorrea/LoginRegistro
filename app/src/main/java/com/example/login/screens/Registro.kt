package com.example.login.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun Registro(onBack: () -> Unit) {

    val ctx = LocalContext.current
    val db = AppDatabase.getDatabase(ctx)

    val usuarioViewModel: UsuarioViewModel = viewModel(
        factory = UsuarioViewModelFactory(db)
    )
    val state = usuarioViewModel.uiState.collectAsState()

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
            value = state.value.user,
            onValueChange = {usuarioViewModel.updateUser(it)},
            label = {
                Text(text = "Usuário")
            },
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
            value = state.value.senha,
            onValueChange = {usuarioViewModel.updateSenha(it)},
            label = {
                Text(text = "Senha")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
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
            value = state.value.email,
            onValueChange = {usuarioViewModel.updateEmail(it)},
            label = {
                Text(text = "E-mail")
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                usuarioViewModel.save()
                Toast.makeText(ctx, "Usuário salvo",
                    Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Salvar")
            }
            Button(onClick = {
                usuarioViewModel.savenew()
                Toast.makeText(ctx, "Usuário salvo",
                    Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Salvar Novo")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu(){
    Registro(onBack = {})
}

