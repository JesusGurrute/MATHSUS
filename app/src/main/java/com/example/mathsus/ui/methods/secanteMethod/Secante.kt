package com.example.mathsus.ui.methods.secanteMethod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mathsus.ui.methods.Metodo
import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions.abs

@Composable
fun Secante(
    a: Double,
    b: Double,
    f: String,
    error: Double,
    maxIterations: Int
) {
    var xiPlus1: Double? = null
    var root: Double? = null
    var contador = 0
    var error: Double
    var maxIterations = maxIterations

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column {

            Row {
                Text(text = " k ")
                Text(text = "    x_k       ")
                Text(text = "    x_(k-1)       ")
                Text(text = "     f(x_k)     ")
            }

            val fa = Metodo(a = a, f = f)
            val fb = Metodo(a = b, f = f)

            for (iteration in 1..maxIterations) {
                contador++

                val roundxi_1 = String.format("%.4f", a)
                val roundxi = String.format("%.4f", b)

                val fxi_1 = Metodo(a = a, f = f)
                val roundfxi_1 = String.format("%.4f", fxi_1)
                val fxi = Metodo(a = b, f = f)
                val roundfxi = String.format("%.4f", fxi)

                xiPlus1 = b - (fxi * (a - b)) / (fxi_1 - fxi)
                val roundxiPlus1 = String.format("%.4f", xiPlus1)
                error = abs((xiPlus1!! - b) / xiPlus1!!) * 100
                val rounderror = String.format("%.4f", error)

                Row {
                    Text(text = " $contador ")
                    Text(text = " $roundxi_1     ")
                    Text(text = " $roundxi     ")
                    Text(text = " $roundfxi_1     ")
                    Text(text = " $roundfxi     ")
                    Text(text = " $roundxiPlus1     ")
                    Text(text = " $rounderror     ")
                }

                if (Math.abs(xiPlus1!! - b) < error.toDouble()) {
                    root = xiPlus1
                    break
                }
                val a = b
                val b = xiPlus1 as Double
            }
            val roundroot = String.format("%.4f", root)
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "La raíz de f(x) = $f es $roundroot ")
            }
        }
    }
}
