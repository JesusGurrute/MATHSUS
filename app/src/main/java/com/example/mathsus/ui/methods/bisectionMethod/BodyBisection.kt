package com.example.mathsus.ui.methods.bisectionMethod

import android.widget.Toast
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
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun BodyBisection() {
    val wallBisection =
        "https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/c/e/ce82ada4d9e8591f01abefebfab0dba4a8228eee.png"

    Box {
        Image(
            painter = rememberAsyncImagePainter(model = wallBisection),
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
            var funcion = remember { mutableStateOf("") }
            var a = remember { mutableStateOf("") }
            var b = remember { mutableStateOf("") }
            var error = remember { mutableStateOf("") }
            var bandera = remember { mutableStateOf("") }
            //var maxIterations = remember { mutableStateOf("") }
            var context = LocalContext.current

            OutlinedTextField(
                label = { Text(text = "Ingrese la función") },
                value = funcion.value,
                //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, textColor = Color.Blue),
                onValueChange = {
                    if (funcion.value.length <= 30)
                        funcion.value = it
                },
                shape = RoundedCornerShape(size = 8.dp)
            )
            Row {
                OutlinedTextField(
                    label = { Text(text = "a") },
                    value = a.value,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number
                    ),
                    //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, textColor = Color.Blue),
                    onValueChange = {
                        a.value = it
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    label = { Text(text = "b") },
                    value = b.value,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        keyboardType = KeyboardType.Number
                    ),
                    //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, textColor = Color.Blue),
                    onValueChange = {
                        b.value = it
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
                    //colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White, textColor = Color.Blue),
                    onValueChange = {
                        error.value = it
                    },
                    shape = RoundedCornerShape(size = 8.dp),
                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                /*
                 OutlinedTextField(
                                    label = { Text(text = "Iterar") },
                                    value = maxIterations.value,
                                    keyboardOptions = KeyboardOptions(
                                        capitalization = KeyboardCapitalization.Sentences,
                                        keyboardType = KeyboardType.Number
                                    ),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.White,
                                        //textColor = Color.Blue
                                    ),
                                    onValueChange = {
                                        maxIterations.value = it
                                    },
                                    shape = RoundedCornerShape(size = 8.dp),
                                    modifier = Modifier.size(width = 80.dp, height = 60.dp)
                                )
                 */

            }
            Button(
                onClick = {
                    if (a.value.isEmpty() || b.value.isEmpty() || error.value.isEmpty() || funcion.value.isEmpty()) {
                        Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT).show()
                    } else {
                        bandera.value = a.value
                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Calcular")
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (bandera.value.isEmpty()) {
                    Text(text = "Al llenar todas las casillas, oprima el boton 'calcular' ")
                } else {
                    Bisection(
                        f = funcion.value,
                        a = a.value.toDouble(),
                        b = b.value.toDouble(),
                        epsilon = error.value.toDouble(),
                        //niter = maxIterations.value.toInt()
                    )
                }
            }
        }
    }
}
