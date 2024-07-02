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
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)

    ) {

        var funcion = remember { mutableStateOf("") }
        var x_i = remember { mutableStateOf("") }
        var maxIter = remember { mutableStateOf("") }
        var bandera = remember { mutableStateOf("") }
        var context = LocalContext.current


        OutlinedTextField(
            label = { Text(text = "Ingrese la función") },
            value = funcion.value,
            //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, //textColor = Color.Blue),
            onValueChange = {
                if (funcion.value.length <= 30)
                    funcion.value = it
            },
            shape = RoundedCornerShape(size = 8.dp)
        )

        Row {
            OutlinedTextField(
                label = { Text(text = "X") },
                value = x_i.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, //textColor = Color.Blue),
                onValueChange = {
                    x_i.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 80.dp, height = 60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                label = { Text(text = "Iteraciones") },
                value = maxIter.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, //textColor = Color.Blue),
                onValueChange = {
                    maxIter.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 150.dp, height = 60.dp)
            )

        }

        Button(
            onClick = {
                if (x_i.value.isEmpty() || maxIter.value.isEmpty() || funcion.value.isEmpty()) {
                    Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT).show()
                } else {
                    bandera.value = x_i.value
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
                    x_i = x_i.value.toDouble(),
                    f = funcion.value,
                    maxIter = maxIter.value.toInt()
                )

            }
        }
    }
}

