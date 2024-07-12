package com.example.mathsus.ui.methods.bisectionMethod

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mathsus.ui.methods.FunctionGraph


@Composable
fun BodyBisection() {

    val funcion = remember { mutableStateOf("") }
    val a = remember { mutableStateOf("") }
    val b = remember { mutableStateOf("") }
    val error = remember { mutableStateOf("") }
    val bandera1 = remember { mutableStateOf("") }
    val bandera2 = remember { mutableStateOf("") }

    val context = LocalContext.current
    val zoom = remember { mutableFloatStateOf(1f) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val (textBoxes, button, graph) = createRefs()

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(textBoxes) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .statusBarsPadding()
                            .padding(16.dp)
                    ) {
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
                                label = { Text(text = "a") },
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
                                label = { Text(text = "b") },
                                value = b.value,
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    keyboardType = KeyboardType.Number
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
                                value = error.value,
                                keyboardOptions = KeyboardOptions(
                                    capitalization = KeyboardCapitalization.Sentences,
                                    keyboardType = KeyboardType.Number
                                ),
                                onValueChange = {
                                    error.value = it
                                },
                                shape = RoundedCornerShape(size = 8.dp),
                                modifier = Modifier.size(width = 80.dp, height = 60.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                        ) {
                            Button(
                                onClick = {
                                    if (a.value.isEmpty() || b.value.isEmpty() || error.value.isEmpty() || funcion.value.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "No deje datos vacios",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        bandera1.value = a.value
                                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) {
                                Text(text = "Calcular")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    if (funcion.value.isEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "Ingrese el la función a calcular",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        bandera2.value = funcion.value
                                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ) {
                                Text(text = "Graficar")
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(button) {
                            top.linkTo(textBoxes.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    Column {
                        if (bandera1.value.isEmpty()) {
                            Text(text = "Al llenar todas las casillas, oprima el boton 'calcular' ")
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Bisection(
                                    f = funcion.value,
                                    a = a.value.toDouble(),
                                    b = b.value.toDouble(),
                                    epsilon = error.value.toDouble(),
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .constrainAs(graph) {
                            top.linkTo(button.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {
                    if (bandera2.value.isNotEmpty()) {
                        FunctionGraph(funcion = funcion.value, zoom = zoom.floatValue)
                    }
                }
            }
        }
    }
}








