package com.example.mathsus.ui.methods.newtonMethod

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
        val x_k = remember { mutableStateOf("") }
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
                value = x_k.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    x_k.value = it
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

        Button(
            onClick = {
                if (x_k.value.isEmpty() || funcion.value.isEmpty()) {
                    Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    bandera.value = x_k.value
                    Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = "Calcular", color = Color.White)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (bandera.value.isEmpty()) {
                Text(text = "Al llenar todas las casillas, oprima el boton 'calcular'")
            } else {
                NewtonRaphson(
                    x = x_k.value.toDouble(),
                    f = funcion.value,
                    error = error.value.toDouble()

                    )

            }
        }
    }
}

