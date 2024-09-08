package com.example.mathsus.ui.methods.bisectionMethod

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathsus.R
import com.example.mathsus.ui.features.nav_menu_bisection.ResultadoBisection
import com.example.mathsus.ui.methods.secanteMethod.CurvedBorderText
import com.example.mathsus.ui.methods.secanteMethod.evaluarFuncion
import kotlin.math.abs

@Composable
fun BodyBisection() {
    val colorScheme = MaterialTheme.colorScheme
    val funcion = remember { mutableStateOf("") }
    val a = remember { mutableStateOf("") }
    val b = remember { mutableStateOf("") }
    val error = remember { mutableStateOf("") }
    val bandera1 = remember { mutableStateOf("") }
    val bandera2 = remember { mutableStateOf("") }

    val context = LocalContext.current
    val zoom = remember { mutableFloatStateOf(1f) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(10.dp)
                .background(shape = RoundedCornerShape(16.dp), color = colorScheme.background)
                .navigationBarsPadding()
                .padding(10.dp)
        ) {
            OutlinedTextField(
                label = { Text(text = "Ingrese la funcion") },
                value = funcion.value,
                onValueChange = {
                    if (funcion.value.length <= 30)
                        funcion.value = it
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.fillMaxWidth()
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
                    modifier = Modifier.size(width = 100.dp, height = 60.dp)
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
                    modifier = Modifier.size(width = 100.dp, height = 60.dp)
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
                    modifier = Modifier.size(width = 160.dp, height = 60.dp)
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
                            Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                ) {
                    Text(text = "Calcular")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Nuevo botón para limpiar los campos
                Button(
                    onClick = {
                        funcion.value = ""
                        a.value = ""
                        b.value = ""
                        error.value = ""
                        bandera1.value = ""
                        Toast.makeText(context, "Campos limpios", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(text = "Limpiar")
                }
            }
            if (bandera1.value.isEmpty()) {
                Text(
                    text = "Al llenar todas las casillas, oprima el boton 'calcular'",
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
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
}

@Composable
fun PasoBodyBisection() {
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
        val funcion = remember { mutableStateOf("") }
        val a = remember { mutableStateOf("") }
        val b = remember { mutableStateOf("") }
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
            label = { Text(text = "Ingrese la función") },
            value = funcion.value,
            onValueChange = {
                if (funcion.value.length <= 30)
                    funcion.value = it
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
                text = "2. Este método requiere un intervalo [a,b] donde la función sea continua y exista un cambio de signo. Además, un valor de error que permite finalizar el proceso.",
                color = colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
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
        val results = remember { mutableStateListOf<ResultadoBisection>() }
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
                            ) {
                                CurvedBorderText(
                                    text = "a",
                                    textColor = Color.White,
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 12.dp,
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
                                    text = "b",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 12.dp,
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
                                    text = "m${resultado.iteracion}",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 12.dp,
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
                                    text = "f(a)",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 12.dp,
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
                                    text = "f(b)",
                                    textColor = Color.White, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 14.sp,
                                    paddingStart = 12.dp,
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
                                    text = "f(m${resultado.iteracion})",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.azulunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = "${resultado.a}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = "${resultado.b}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = resultado.c.toString(),
                                    textColor = Color.Red,
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = "${evaluarFuncion(resultado.a, funcion.value)}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = "${evaluarFuncion(resultado.b, funcion.value)}",
                                    textColor = Color.Black, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                                    text = "${
                                        evaluarFuncion(
                                            resultado.c,
                                            funcion.value
                                        )
                                    }",
                                    textColor = Color.Red, // Color del texto personalizado
                                    backgroundColor = colorResource(id = R.color.grisunicauca),
                                    fontSize = 10.sp,
                                    paddingStart = 12.dp,
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
                    val errorf = (resultado.b - resultado.a)/2
                    Spacer(modifier = Modifier.height(16.dp))
                    if (evaluarFuncion(resultado.a, funcion.value) * evaluarFuncion(
                            resultado.c,
                            funcion.value
                        ) < 0
                    ) {
                        Text(
                            text = "Observé que f(m${resultado.iteracion}) < f(b) y el signo de f(a) con el signo de f(m${resultado.iteracion}) son opuestos, por lo que el nuevo intervalo se define como [a,m]. De este modo, el valor de 'b' toma el valor de m${resultado.iteracion}.",
                            color = colorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                    } else {
                        Text(
                            text = "Observé que f(a) < f(m${resultado.iteracion}) y el signo de f(m${resultado.iteracion}) con el signo f(b) son opuestos, por lo que el nuevo intervalo se define como [m,b]. De este modo, el valor de 'a' toma el valor de m${resultado.iteracion}.",
                            color = colorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Justify
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ademas:",
                        color = colorScheme.onBackground,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            Text(
                                text = "       b - a \n" +
                                        "error =    ----------   =  \n  " +
                                        "     2", modifier = Modifier.fillMaxWidth(),
                                color = colorScheme.onBackground,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box {
                            Text(
                                text = "${b.value} - ${a.value} \n" +
                                        "      ----------     =   \n  " +
                                        "2", modifier = Modifier.fillMaxWidth(),
                                color = colorScheme.onBackground,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box {
                            Text(
                                text = "  \n" +
                                        " $errorf \n  " +
                                        " ", modifier = Modifier.fillMaxWidth(),
                                color = colorScheme.onBackground,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // Aquí es donde hacemos el cambio
                    if (errorf < error.value.toDouble()) {
                        Text(
                            text = "aquí $errorf < ${error.value.toDouble()}, por lo que el error es menor al permitido. De manera que, se da por terminado el proceso.",
                            color = colorScheme.onBackground,
                            textAlign = TextAlign.Justify
                        )
                    } else {
                        Text(
                            text = "tal que $errorf > ${error.value.toDouble()}, por lo que se procede a calcular m${resultado.iteracion + 1}.",
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
                    text = "${currentIndex + 3}. Dado el intervalo [a,b] = [${a.value},${b.value}]:\n" +
                            "\nse calcula el punto de corte m${currentIndex + 1} con la siguiente ecuación:",
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Text(
                        text = "       a + b \n" +
                                "m${currentIndex + 1} =    ----------   =  \n  " +
                                "     2", modifier = Modifier.fillMaxWidth(),
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                }
                Box {
                    Text(
                        text = "${a.value} + ${b.value} \n" +
                                "      ----------     =   \n  " +
                                "2", modifier = Modifier.fillMaxWidth(),
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box {
                    Text(
                        text = "  \n" +
                                " Aquí el valor \n  " +
                                " ", modifier = Modifier.fillMaxWidth(),
                        color = colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (a.value.isEmpty() || b.value.isEmpty() || funcion.value.isEmpty() || error.value.isEmpty()) {
                            Toast.makeText(context, "No deje datos vacíos", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            bandera.value = a.value
                            Toast.makeText(
                                context,
                                "Calculando x${currentIndex + 2}",
                                Toast.LENGTH_SHORT
                            ).show()

                            val aDouble = a.value.toDouble()
                            val bDouble = b.value.toDouble()
                            val result = calcularX2Bisection(
                                a = aDouble,
                                b = bDouble,
                                funcion = funcion.value
                            )
                            val absx1x2 = abs(bDouble - result)

                            results.add(
                                ResultadoBisection(
                                    currentIndex + 1,
                                    aDouble,
                                    bDouble,
                                    result
                                )
                            )
                            currentIndex++
                            // Update a and b for the next iteration
                            if (evaluarFuncion(aDouble, funcion.value) * evaluarFuncion(
                                    result,
                                    funcion.value
                                ) < 0
                            ) {
                                b.value = result.toString()
                            } else {
                                a.value = result.toString()
                            }
                            // Check if we should continue
                            shouldContinue =
                                currentIndex < totalIterations && absx1x2 > error.value.toDouble()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                    enabled = shouldContinue
                ) {
                    val aux = currentIndex + 2
                    Text(text = "Iteración ${aux - 1}")
                }
            }

        } else {
            Column {

                Text(
                    text = "La raíz de la ecuación f(x) = ${funcion.value} es:",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = colorScheme.onBackground,
                )
                Text(
                    text = "x ≈ ${results.lastOrNull()?.c ?: "No disponible"}",
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
                    funcion.value = ""
                    a.value = ""
                    b.value = ""
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
fun calcularX2Bisection(a: Double, b: Double, funcion: String): Double {
    val x2 = (a + b) / 2
    return String.format("%.4f", x2).toDouble()
}

/*
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
 */


/*
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
                        Toast.makeText(context, "Calculando", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text(text = "Graficar")
            }

 */




