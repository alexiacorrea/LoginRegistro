package com.example.login.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Viagem() {
    Text(
        text = "VIAGEM",
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        modifier = Modifier.fillMaxWidth()
    )
}