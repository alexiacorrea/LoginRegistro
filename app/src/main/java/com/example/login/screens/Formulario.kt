package com.example.login.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.login.bd.AppDatabase
import com.example.login.viewmodels.ViagemViewModel
import com.example.login.viewmodels.ViagemViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(onBack:()-> Unit, id: Long?) {

    Scaffold(
    ){

        val ctx = LocalContext.current
        val db = AppDatabase.getDatabase(ctx)

        val viagemViewModel: ViagemViewModel = viewModel(
            factory = ViagemViewModelFactory(db)
        )

        LaunchedEffect(id){
            if (id != null){
                val viagem = viagemViewModel.findById(id)
                viagem?.let { viagemViewModel.setUiState(it) }
            }
        }

        val state = viagemViewModel.uiState.collectAsState()

        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        SnackbarHost(
            hostState = snackbarHostState
        ) {}


        val showDatePickerDialogInicio = remember {
            mutableStateOf(false)
        }
        val selectedDateIni = remember {
            mutableStateOf("")
        }
        val datePickerStateInicio = rememberDatePickerState()



        val showDatePickerDialogFim = remember {
            mutableStateOf(false)
        }
        val selectedDateFim = remember {
            mutableStateOf("")
        }
        val datePickerStateFim = rememberDatePickerState()

        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Destino",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                )
            }

            Row {
                OutlinedTextField(
                    value = state.value.destino,
                    onValueChange = {viagemViewModel.updateDestino(it)},
                    label = {Text(text = "Destino")},
                    modifier = Modifier
                        .weight(4f)
                        .padding(top = 10.dp)
                )
            }

            Row {
                Text(
                    text = "Tipo",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = state.value.tipo == 0,
                    onClick = { viagemViewModel.updateTipo(0) } ,
                    modifier = Modifier
                        .weight(0.5f)
                )

                Text(
                    text = "Lazer",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                )

                RadioButton(
                    selected = state.value.tipo == 1,
                    onClick = { viagemViewModel.updateTipo(1) },
                    modifier = Modifier
                        .weight(0.5f)
                )

                Text(
                    text = "Negócios",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)

                )
            }

            Row {

                Text(
                    text = "Data inicial",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(top = 16.dp)
                )
            }

            Row {

                if (showDatePickerDialogInicio.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerDialogInicio.value = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerStateInicio
                                        .selectedDateMillis?.let { millis ->
                                            viagemViewModel.updateDataIni(millis.toBrazilianDateFormat())
                                        }
                                    showDatePickerDialogInicio.value = false
                                }) {
                                Text(text = "Escolher data")
                            }
                        },
                        modifier = Modifier
                            .weight(4f)
                    ) {
                        DatePicker(state = datePickerStateInicio)
                    }
                }

                OutlinedTextField(
                    value = state.value.dataIni,
                    onValueChange = {viagemViewModel.updateDataIni(it)},
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                showDatePickerDialogInicio.value = true
                            }
                        },
                    readOnly = true
                )
            }

            Row {

                Text(
                    text = "Data final",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(top = 16.dp)
                )
            }

            Row {

                if (showDatePickerDialogFim.value) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerDialogFim.value = false },
                        confirmButton = {
                            Button(
                                onClick = {
                                    datePickerStateFim
                                        .selectedDateMillis?.let { millis ->
                                            viagemViewModel.updateDataFim(millis.toBrazilianDateFormat())
                                        }
                                    showDatePickerDialogFim.value = false
                                }) {
                                Text(text = "Escolher data")
                            }
                        },
                        modifier = Modifier
                            .weight(4f)
                    ) {
                        DatePicker(state = datePickerStateFim)
                    }
                }

                OutlinedTextField(
                    value = state.value.dataFim,
                    onValueChange = {viagemViewModel.updateDataFim(it)},
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                showDatePickerDialogFim.value = true
                            }
                        },
                    readOnly = true
                )
            }

            Row {

                Text(
                    text = "Orçamento",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(top = 16.dp)
                )
            }

            Row {
                OutlinedTextField(
                    value = state.value.orcamento.toString(),
                    onValueChange = {viagemViewModel.updateOrcamento(it.toDouble())},
                    label = { Text(text = "Orçamento") },
                    modifier = Modifier
                        .weight(4f)
                        .padding(top = 10.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){

                Button(onClick = {
                    viagemViewModel.savenew()
                    onBack()
                    Toast.makeText(ctx, "Viagem salva",
                        Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Salvar Nova")
                }
            }
        }
    }
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}
