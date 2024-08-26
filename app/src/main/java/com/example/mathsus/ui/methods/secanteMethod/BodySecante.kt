package com.example.mathsus.ui.methods.secanteMethod

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathsus.R
import com.example.mathsus.ui.features.nav_menu_secante.ResultadoSecante
import com.example.mathsus.ui.methods.FunctionGraph
import com.example.mathsus.ui.methods.GraphViewModel
import org.mariuszgromada.math.mxparser.Function
import kotlin.math.abs

@Composable
fun BodySecante() {
    val viewModel: GraphViewModel = viewModel()
    val showGraph = remember { mutableStateOf(false) }
    //val wallSecante = "https://kodular-community.s3.dualstack.eu-west-1.amazonaws.com/original/3X/c/e/ce82ada4d9e8591f01abefebfab0dba4a8228eee.png"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(10.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            )
            .navigationBarsPadding()
            .padding(10.dp)

    ) {

        val f = remember { mutableStateOf("") }
        val a = remember { mutableStateOf("") }
        val b = remember { mutableStateOf("") }
        val x2 = remember { mutableStateOf("") }
        val bandera = remember { mutableStateOf("") }
        val context = LocalContext.current

        OutlinedTextField(
            label = { Text(text = "Ingrese la funcion") },
            value = f.value,
            onValueChange = {
                if (f.value.length <= 30)
                    f.value = it
            },
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier.fillMaxWidth()
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
                modifier = Modifier.size(width = 100.dp, height = 60.dp)
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
                modifier = Modifier.size(width = 100.dp, height = 60.dp)
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
                modifier = Modifier.size(width = 160.dp, height = 60.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(
                onClick = {
                    if (a.value.isEmpty() || b.value.isEmpty() || x2.value.isEmpty() || f.value.isEmpty()) {
                        Toast.makeText(context, "No deje datos vacios", Toast.LENGTH_SHORT).show()
                    } else {
                        bandera.value = a.value
                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.azulunicauca)
                )
            ) {
                Text(text = "Calcular")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    viewModel.llamarGraphCoroutine(f.value)
                    showGraph.value = true
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.azulunicauca)
                )
            ) {
                Text(text = "Graficar")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            if (bandera.value.isEmpty()) {
                //Text(text = "Al llenar todas las casillas, oprima el boton 'calcular'")
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

@Composable
fun PasoBodySecante() {
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(2.dp)
            .background(colorScheme.background)
            //.background(shape = RoundedCornerShape(16.dp), color = Color.White)
            .navigationBarsPadding()
            .padding(4.dp)
    ) {
        Text(
            text = "Avanza a tu propio ritmo",
            color = colorScheme.onBackground,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        val f = remember { mutableStateOf("") }
        val x0 = remember { mutableStateOf("") }
        val x1 = remember { mutableStateOf("") }
        val error = remember { mutableStateOf("") }
        val bandera = remember { mutableStateOf("") }
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "1. Ingrese la función",
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ejemplo de una función bien formada:",
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "f(x) = 2 * sin(x) + log(x, 10) - 3*x^2 + pi",
                    modifier = Modifier.fillMaxWidth(),
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
        OutlinedTextField(
            label = { Text(text = "Ingrese la funcion") },
            value = f.value,
            onValueChange = {
                if (f.value.length <= 30)
                    f.value = it
            },
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Agrega un margen lateral si es necesario
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "2. Elija los puntos iniciales x0, x1 y un valor de error. Si |x1-x2| < error, el proceso termina.",
                color = colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                label = { Text(text = "X0") },
                value = x0.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = {
                    x0.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 80.dp, height = 60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                label = { Text(text = "X1") },
                value = x1.value,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Number
                ),
                /*
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                 */
                onValueChange = {
                    x1.value = it
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
                /*
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
                 */
                onValueChange = { newValue ->
                    // Primero reemplazamos las comas por puntos
                    val processedValue = newValue.replace(',', '.')
                    // Luego verificamos si el valor resultante es un número decimal válido
                    if (processedValue.isEmpty() || processedValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                        error.value = processedValue
                    }
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 130.dp, height = 60.dp)
            )
        }
        var currentIndex by remember { mutableIntStateOf(0) }
        val results = remember { mutableStateListOf<ResultadoSecante>() }
        val totalIterations = 200
        var shouldContinue by remember { mutableStateOf(true) }
        results.forEach { resultado ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "Iteración: ${resultado.iteracion}",
                        color = colorResource(id = R.color.rojounicauca),
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .width(400.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .fillMaxWidth()
                                    .width(IntrinsicSize.Min)
                                    .horizontalScroll(rememberScrollState())
                                    .background(colorScheme.background)
                                    //.background(shape = RoundedCornerShape(4.dp), color = colorResource(id = R.color.azulunicauca))
                            ) {
                                CurvedBorderText(
                                    text = "x0",
                                    textColor = Color.White,
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "x1",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "x${resultado.iteracion + 1}",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "f(x0)",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "f(x1)",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "f(x${resultado.iteracion + 1})",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .width(IntrinsicSize.Min)
                                    .horizontalScroll(rememberScrollState())
                                    .background(
                                        shape = RoundedCornerShape(4.dp),
                                        color = colorResource(id = R.color.grisunicauca)
                                    )
                            ) {
                                CurvedBorderText(
                                    text = "${resultado.x0}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "${resultado.x1}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = resultado.x2.toString(),
                                    textColor = Color.Red,
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "${evaluarFuncion(resultado.x0, f.value)}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "${evaluarFuncion(resultado.x1, f.value)}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                                CurvedBorderText(
                                    text = "${evaluarFuncion(resultado.x2.toDouble(), f.value)}",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 20.dp,
                                    paddingEnd = 12.dp,
                                    paddingTop = 6.dp,
                                    paddingBottom = 6.dp,
                                    borderColor = Color.Black,
                                    borderWidth = 1.dp, // Grosor del borde
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        }
                    }
                    val absx1x2 = abs(resultado.x1 - resultado.x2.toDouble())
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Note que: ",
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = " |x1 - x${resultado.iteracion + 1}| = |${resultado.x1} - ${resultado.x2} | =  $absx1x2 > ${error.value.toDouble()}",
                        color = colorScheme.onBackground,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    // Aquí es donde hacemos el cambio
                    if (absx1x2 < error.value.toDouble()) {
                        Text(
                            text = "La diferencia es menor a error y no se procede a calcular x${resultado.iteracion + 2}.",
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Justify
                        )
                    } else {
                        Text(
                            text = "De manera que, se procede a calcular x${resultado.iteracion + 2}.",
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }

        if (shouldContinue) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "${currentIndex + 3}. Dado:\n" +
                            "\nx0 = ${x0.value}\n" +
                            "x1 = ${x1.value}\n" +
                            "\nse calcula el punto de corte x${currentIndex + 2} con la siguiente ecuación:",
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "   x1 - x0 \n" +
                        "  x${currentIndex + 2} =  x1 -   ----------------- * f(x1)     \n  " +
                        "   f(x1) - f(x0)", modifier = Modifier.fillMaxWidth(),
                color = colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Para ello, oprima el botón",
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Justify
                    )
                }
                Box {
                    Button(
                        onClick = {
                            if (x0.value.isEmpty() || x1.value.isEmpty() || f.value.isEmpty() || error.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "No deje datos vacios",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                bandera.value = x0.value
                                Toast.makeText(
                                    context,
                                    "Calculando x${currentIndex + 2}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                val x0Double = x0.value.toDouble()
                                val x1Double = x1.value.toDouble()
                                val result = calcularX2Secante(
                                    x0 = x0Double,
                                    x1 = x1Double,
                                    f = f.value
                                )
                                val absx1x2 = abs(x1Double - result)
                                results.add(
                                    ResultadoSecante(
                                        currentIndex + 1,
                                        x0Double,
                                        x1Double,
                                        result
                                    )
                                )
                                currentIndex++
                                // Actualizar x0 y x1 para la próxima iteración
                                x0.value = x1.value
                                x1.value = result.toString()
                                // Verificar si debemos continuar
                                shouldContinue =
                                    currentIndex < totalIterations && absx1x2 > error.value.toDouble()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                        enabled = shouldContinue
                    ) {
                        val aux = currentIndex + 2
                        Text(text = "Hallar x$aux")
                    }
                }
            }
        }else {
            Column {

                Text(
                    text = "La raíz de la ecuación f(x) = ${f.value} es:",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = colorScheme.onBackground,
                )
                Text(
                    text = "x ≈ ${results.lastOrNull()?.x2 ?: "No disponible"}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorScheme.onBackground,
                )
            }
        }
        if (!shouldContinue) {
            Text(
                text = "El proceso ha terminado.",
                color = colorScheme.onBackground,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = {
                    // Reiniciar todas las variables
                    f.value = ""
                    x0.value = ""
                    x1.value = ""
                    error.value = ""
                    bandera.value = ""
                    currentIndex = 0
                    results.clear()
                    shouldContinue = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Realizar nuevo cálculo")
            }
        }
    }
}

@SuppressLint("DefaultLocale")
fun calcularX2Secante(x0: Double, x1: Double, f: String): Double {

    val fx0 = evaluarFuncion(x0, f)
    val fx1 = evaluarFuncion(x1, f)
    if (fx1 - fx0 == 0.0) {
        throw IllegalArgumentException("División por cero detectada durante el cálculo.")
    }
    val x2 = x1 - ((x1 - x0) * fx1 / (fx1 - fx0))

    val roundx2 = String.format("%.4f", x2)

    return roundx2.toDouble()
}

@SuppressLint("DefaultLocale")
fun evaluarFuncion(a: Double, f: String): Double {
    val f = Function("f", f, "x")
    val fa = org.mariuszgromada.math.mxparser.Expression("f(${a})", f).calculate()
    val roundfa = String.format("%.4f", fa)
    return roundfa.toDouble()
}

@Composable
fun CurvedBorderText(
    text: String,
    textColor: Color = Color.White,
    backgroundColor: Color = colorResource(id = R.color.azulunicauca),
    borderColor: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    borderWidth: Dp = 1.dp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
    style: TextStyle = TextStyle.Default,
    fontSize: TextUnit = TextUnit.Unspecified,
    paddingStart: Dp = 8.dp,
    paddingEnd: Dp = 8.dp,
    paddingTop: Dp = 8.dp,
    paddingBottom: Dp = 8.dp,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = textColor,
        fontWeight = fontWeight,
        textAlign = textAlign,
        style = style.copy(fontSize = if (fontSize != TextUnit.Unspecified) fontSize else style.fontSize),        modifier = modifier
            .fillMaxSize()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(borderRadius)
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(borderRadius)
            )
            .padding(start = paddingStart, end = paddingEnd, top = paddingTop, bottom = paddingBottom)
    )
}

/*
HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
 */

