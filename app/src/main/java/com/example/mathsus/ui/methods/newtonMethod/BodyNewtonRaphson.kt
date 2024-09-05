package com.example.mathsus.ui.methods.newtonMethod

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import com.example.mathsus.ui.features.nav_menu_newton.ResultadoNewton
import com.example.mathsus.ui.methods.Derivada
import com.example.mathsus.ui.methods.secanteMethod.CurvedBorderText
import com.example.mathsus.ui.methods.secanteMethod.evaluarFuncion
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function
import kotlin.math.abs


@Composable
fun BodyNewtonRaphson() {
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(10.dp)
            .background(shape = RoundedCornerShape(16.dp), color = colorScheme.background)
            .navigationBarsPadding()
            .padding(10.dp)
    ) {

        val funcion = remember { mutableStateOf("") }
        val xk = remember { mutableStateOf("") }
        val error = remember { mutableStateOf("") }
        val bandera = remember { mutableStateOf("") }
        val context = LocalContext.current
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
                modifier = Modifier.size(width = 150.dp, height = 60.dp)
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
                modifier = Modifier.size(width = 200.dp, height = 60.dp)
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
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
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
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),

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

@Composable
fun PasoBodyNewton() {
    val colorScheme = MaterialTheme.colorScheme
    val context = LocalContext.current
    var function by remember { mutableStateOf("") }
    var x0 by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var currentIndex by remember { mutableIntStateOf(0) }
    val results = remember { mutableStateListOf<ResultadoNewton>() }
    var shouldContinue by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(6.dp)
            .background(colorScheme.background)
            .navigationBarsPadding()
    ) {
        Text(
            text = "Avanza a tu propio ritmo",
            color = colorScheme.onBackground,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        // Input para la función
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
                    text = "f(x) = x^2 - 4",
                    modifier = Modifier.fillMaxWidth(),
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
        OutlinedTextField(
            label = { Text(text = "Ingrese la función") },
            value = function,
            onValueChange = {
                if (it.length <= 30)
                    function = it
            },
            shape = RoundedCornerShape(size = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        // Input para x0 y error
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "2. Ingrese el valor inicial x0 y el error deseado",
                color = colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                label = { Text(text = "x0") },
                value = x0,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { x0 = it },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 80.dp, height = 60.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                label = { Text(text = "Error") },
                value = error,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    val processedValue = newValue.replace(',', '.')
                    if (processedValue.isEmpty() || processedValue.matches(Regex("^\\d*\\.?\\d*$"))) {
                        error = processedValue
                    }
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier.size(width = 130.dp, height = 60.dp)
            )
        }

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
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                ResultHeaderCell(text = "x${resultado.iteracion}")
                                ResultHeaderCell(text = "f(x${resultado.iteracion})")
                                ResultHeaderCell(text = "f'(x${resultado.iteracion})")
                                ResultHeaderCell(text = "x${resultado.iteracion + 1}")
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
                                ResultCell(text = "${resultado.x}")
                                ResultCell(text = "${resultado.fx}")
                                ResultCell(text = "${resultado.dfx}")
                                ResultCell(text = "${resultado.nextX}")
                            }
                        }
                    }
                }
            }
        }

        if (shouldContinue) {
            CalculationSection(currentIndex, x0)
            CalculateButton(
                currentIndex,
                shouldContinue,
                { shouldContinue = it },
                { currentIndex = it },
                function,
                x0,
                error,
                results,
                context
            )
        }// Mostrar resultados
        else {
            FinalResult(function, results)
            RestartButton(
                { function = "" },
                { x0 = "" },
                { error = "" },
                { currentIndex = 0 },
                { results.clear() },
                { shouldContinue = true }
            )
        }
    }
}


@Composable
fun ResultHeaderCell(text: String) {
    CurvedBorderText(
        text = text,
        textColor = Color.White,
        backgroundColor = colorResource(id = R.color.azulunicauca),
        fontSize = 14.sp,
        paddingStart = 20.dp,
        paddingEnd = 12.dp,
        paddingTop = 6.dp,
        paddingBottom = 6.dp,
        borderColor = Color.Black,
        borderWidth = 1.dp,
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ResultCell(text: String) {
    CurvedBorderText(
        text = text,
        textColor = Color.Black,
        backgroundColor = colorResource(id = R.color.grisunicauca),
        fontSize = 10.sp,
        paddingStart = 20.dp,
        paddingEnd = 12.dp,
        paddingTop = 6.dp,
        paddingBottom = 6.dp,
        borderColor = Color.Black,
        borderWidth = 1.dp,
        modifier = Modifier
            .wrapContentSize(Alignment.Center)
    )
}

fun NewtonRaphsonCalculator(x: Double, function: String): Triple<Double, Double, Double> {
    val fx = evaluarFuncion1(x, function)
    val dfx = CalculateDerivative(function, x)

    if (dfx == 0.0) {
        throw IllegalArgumentException("La derivada es cero. No se puede continuar.")
    }

    val nextX = x - fx / dfx

    return Triple(
        String.format("%.4f", nextX).toDouble(),
        String.format("%.4f", fx).toDouble(),
        String.format("%.4f", dfx).toDouble()
    )
}
// Esta función no es @Composable
fun evaluarFuncion1(x: Double, function: String): Double {
    val xArgument = Argument("x = $x")
    val expression = Expression(function, xArgument)
    return expression.calculate()
}

fun CalculateDerivative(function: String, x: Double): Double {
    val xArgument = Argument("x = $x")
    val derivativeExpression = "der($function, x)"
    val expression = Expression(derivativeExpression, xArgument)
    return expression.calculate()
}

// Modificar CalculateButton para usar NewtonRaphsonCalculator
@Composable
fun CalculateButton(
    currentIndex: Int,
    shouldContinue: Boolean,
    onShouldContinueChange: (Boolean) -> Unit,
    onCurrentIndexChange: (Int) -> Unit,
    function: String,
    x0: String,
    error: String,
    results: SnapshotStateList<ResultadoNewton>,
    context: android.content.Context
) {
    Button(
        onClick = {
            if (x0.isEmpty() || function.isEmpty() || error.isEmpty()) {
                Toast.makeText(context, "No deje datos vacíos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Calculando x${currentIndex + 1}", Toast.LENGTH_SHORT).show()

                val x = if (currentIndex == 0) x0.toDouble() else results.last().nextX
                val (nextX, fx, dfx) = NewtonRaphsonCalculator(x, function)
                results.add(ResultadoNewton(currentIndex, x, fx, dfx, nextX))
                onCurrentIndexChange(currentIndex + 1)

                // Check if we should continue
                onShouldContinueChange(currentIndex + 1 < 200 && abs(nextX - x) > error.toDouble())
            }
        },
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
        enabled = shouldContinue
    ) {
        Text(text = "Calcular x${currentIndex + 1}")
    }
}

@Composable
fun CalculationSection(currentIndex: Int, x0: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "${currentIndex + 3}. Dado x${currentIndex} = ${if (currentIndex == 0) x0 else "valor previo"}, se calcula:",
            color = colorScheme.onBackground,
            textAlign = TextAlign.Justify
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "x${currentIndex + 1} = x${currentIndex} - f(x${currentIndex}) / f'(x${currentIndex})",
        modifier = Modifier.fillMaxWidth(),
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
    }
}

@Composable
fun FinalResult(function: String, results: List<ResultadoNewton>) {
    Column {
        Text(
            text = "La raíz de la ecuación f(x) = $function es:",
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = colorScheme.onBackground,
        )
        Text(
            text = "x ≈ ${results.lastOrNull()?.nextX ?: "No disponible"}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = colorScheme.onBackground,
        )
    }
}

@Composable
fun RestartButton(
    onFunctionReset: () -> Unit,
    onX0Reset: () -> Unit,
    onErrorReset: () -> Unit,
    onCurrentIndexReset: () -> Unit,
    onResultsReset: () -> Unit,
    onShouldContinueReset: () -> Unit
) {
    Button(
        onClick = {
            onFunctionReset()
            onX0Reset()
            onErrorReset()
            onCurrentIndexReset()
            onResultsReset()
            onShouldContinueReset()
        },
        colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Realizar nuevo cálculo")
    }
}