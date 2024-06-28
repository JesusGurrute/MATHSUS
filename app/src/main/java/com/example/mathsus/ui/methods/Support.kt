package com.example.mathsus.ui.methods

import androidx.compose.runtime.Composable
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.Function

@Composable
fun Metodo(a: Double, f: String): Double {
    var f = Function("f", f, "x")
    var fa = org.mariuszgromada.math.mxparser.Expression("f(${a})", f).calculate()
    return fa
}

@Composable
fun Derivada(a: Double, f: String): Double {
    var df = Expression("der($f, x, ${a})").calculate()
    return df
}