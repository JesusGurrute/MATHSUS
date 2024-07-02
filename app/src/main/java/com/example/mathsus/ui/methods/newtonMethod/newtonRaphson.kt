package com.example.mathsus.ui.methods.newtonMethod

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mathsus.ui.methods.Derivada
import com.example.mathsus.ui.methods.Metodo

@Composable
fun NewtonRaphson(
    x_i: Double,
    f: String,
    maxIter: Int
) {
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(text = " n ")
                Text(text = "    xi       ")
                Text(text = "     f(xi)     ")
                Text(text = "          f'(xi)     ")
                Text(text = "       xi+1     ")
            }
            var contador = 1
            var x_i = x_i
            var f = f
            for (iteration in 1..maxIter) {
                var fxi = Metodo(a = x_i, f = f)
                var dfxi = Derivada(a = x_i, f = f)
                val xiPlus1 = x_i - (Metodo(a = x_i, f = f) / Derivada(a = x_i, f = f))
                val roundxi = String.format("%.4f", x_i)
                val roundfxi = String.format("%.4f", fxi)
                val rounddfxi = String.format("%.4f", dfxi)
                val roundxiPlus1 = String.format("%.4f", xiPlus1)



                Row {
                    Text(text = " $contador ")
                    Text(text = " $roundxi     ")
                    Text(text = " $roundfxi     ")
                    Text(text = " $rounddfxi     ")
                    Text(text = " $roundxiPlus1     ")
                }
                contador++
                x_i = xiPlus1
            }

        }

    }
}
/*

Ejemplo
funcion: 19x^7-6x^2-19
x_i = 3.0
maxIter= 100
 */