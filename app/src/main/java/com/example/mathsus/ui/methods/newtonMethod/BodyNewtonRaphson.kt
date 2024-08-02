package com.example.mathsus.ui.methods.newtonMethod

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun BodyNewtonRaphson() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)

    ) {

        val funcion = remember { mutableStateOf("") }
        val xk = remember { mutableStateOf("") }
        val error = remember { mutableStateOf("") }
        val bandera = remember { mutableStateOf("") }
        val context = LocalContext.current


        OutlinedTextField(
            label = { Text(text = "Ingrese la función") },
            value = funcion.value,
            onValueChange = {
                if (funcion.value.length <= 30)
                    funcion.value = it
            },
            shape = RoundedCornerShape(size = 8.dp)
        )

        Row {
            OutlinedTextField(
                label = { Text(text = "X") },
                value = xk.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    xk.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 80.dp, height = 60.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                label = { Text(text = "Error") },
                value = error.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    error.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 150.dp, height = 60.dp)
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        ) {
            Button(
                onClick = {
                    if (xk.value.isEmpty() || funcion.value.isEmpty()) {
                        Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT).show()
                    } else {
                        bandera.value = xk.value
                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Calcular", color = Color.White)
            }

            Spacer(modifier = Modifier.width(8.dp))
            // Nuevo botón para limpiar los campos
            Button(
                onClick = {
                    funcion.value = ""
                    xk.value = ""
                    error.value = ""
                    bandera.value = ""
                    Toast.makeText(context, "Campos limpios", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(text = "Limpiar")
            }


        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (bandera.value.isEmpty()) {
                Text(text = "Al llenar todas las casillas, oprima el boton 'calcular'")
            } else {
                NewtonRaphson(
                    x = xk.value.toDouble(),
                    f = funcion.value,
                    error = error.value.toDouble()

                )

            }
        }
    }
}

