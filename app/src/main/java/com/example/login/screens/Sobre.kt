package com.example.login.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Sobre() {
    Column {
        Text(
            text = "SOBRE O DESENVOLVEDOR",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Nome: Alexia Correa",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Idade: 22",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Curso: Análise e Desenvolvimento de Sistemas",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Período: 5º Semestre",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}