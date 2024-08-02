package com.example.mathsus.ui.methods.secanteMethod

import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mathsus.ui.methods.FunctionGraph
import com.example.mathsus.ui.methods.GraphViewModel

@Composable
fun BodySecante() {
    val viewModel: GraphViewModel = viewModel()
    val showGraph = remember { mutableStateOf(false) }
    val wallSecante =
        "https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/c/e/ce82ada4d9e8591f01abefebfab0dba4a8228eee.png"
    Box {
        Image(
            painter = rememberAsyncImagePainter(model = wallSecante),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,

            )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .statusBarsPadding()
                .padding(16.dp)

        ) {

            val f = remember { mutableStateOf("") }
            val a = remember { mutableStateOf("") }
            val b = remember { mutableStateOf("") }
            val x2 = remember { mutableStateOf("") }
            val bandera = remember { mutableStateOf("") }
            val context = LocalContext.current

            OutlinedTextField(
                label = { Text(text = "Ingrese la Funcion") },
                value = f.value,
                onValueChange = {
                    if (f.value.length <= 30)
                        f.value = it
                },
                shape = RoundedCornerShape(size = 8.dp)
            )

            Row {
                OutlinedTextField(
                    label = { Text(text = "X0") },
                    value = a.value,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        a.value = it
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    label = { Text(text = "X1") },
                    value = b.value,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    ),

                    onValueChange = {
                        b.value = it
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    label = { Text(text = "Error") },
                    value = x2.value,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                    ),
                    onValueChange = {
                        x2.value = it
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                )
            }
            Row {

                Button(
                    onClick = {
                        if (a.value.isEmpty() || b.value.isEmpty() || x2.value.isEmpty() || f.value.isEmpty()) {
                            Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            bandera.value = a.value
                            Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(text = "Calcular")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        viewModel.llamarGraphCoroutine(f.value)
                        showGraph.value = true
                    }
                ) {
                    Text(text = "Graficar")
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                if (bandera.value.isEmpty()) {
                    Text(text = "Al llenar todas las casillas, oprima el boton 'calcular'")
                } else {
                    Secante(
                        a = a.value.toDouble(),
                        b = b.value.toDouble(),
                        f = f.value,
                        epsilon = x2.value.toDouble()
                    )
                }

                if (showGraph.value) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        FunctionGraph(
                            viewModel = viewModel,
                            modifier = Modifier.fillMaxSize()
                        )
                        IconButton(
                            onClick = { showGraph.value = false },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Close Graph")
                        }
                    }
                }
            }
        }
    }
}


/*
                           // Mostrar la gráfica
                           if (f.value.isNotEmpty()) {
                               Text("Dibujando gráfica para: ${f.value}") // Mensaje de depuración


                           if (bandera2.value.isNotEmpty()) {  // Cambiado a bandera2
                               FunctionGraph(
                                   funcion = f.value,  // Cambiado a f.value
                                   initialZoom = zoom,
                                   modifier = Modifier
                                       .height(300.dp)
                                       .fillMaxWidth()
                               )
                           }
                            */

