package com.example.mathsus.ui.methods.secanteMethod

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.Composable
import com.example.mathsus.ui.methods.Metodo
import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions.abs

@SuppressLint("DefaultLocale")
@Composable
fun Secante(
    a: Double,
    b: Double,
    f: String,
    epsilon: Double,
) {
    var k = 0
    var currentA = a
    var currentB = b
    var fa = Metodo(a = a, f = f)
    var fb = Metodo(a = b, f = f)
    var d: Double

    Column {

        Row {
            Text(text = " k ")
            Text(text = "      x_k       ")
             Text(text = "            f(x_k)     ")
        }

        if (abs(fa) > abs(fb)) {
            currentA = currentB.also { currentB = currentA }
            fa = fb.also { fb = fa }
        }

        for (iteration in 1..200) {

            val roundcurrentA = String.format("%.4e", currentA)
            val partcurrentA = roundcurrentA.split("e")
            val coefficientcurrentA = partcurrentA[0].toDouble()
            val currentACoefficient = if (coefficientcurrentA % 1 == 0.0) {
                coefficientcurrentA.toInt().toString()
            } else {
                partcurrentA[0].replace(Regex("0*$"), "")
            }

            val roundfa = String.format("%.4e", fa)
            val partsfa = roundfa.split("e")
            val coefficientfa = partsfa[0].toDouble()
            val exponentfa = partsfa[1].toInt()
            val faCoefficient = if (coefficientfa % 1 == 0.0) {
                coefficientfa.toInt().toString()
            } else {
                partsfa[0].replace(Regex("0*$"), "")
            }

            Row {
                Text(text = " $k ")
                Text(text = "    $currentACoefficient    ")
                Text(text = "                         $faCoefficient * 10^$exponentfa     ")
            }
            if (abs(fa) > abs(fb)) {
                currentA = currentB.also { currentB = currentA }
                fa = fb.also { fb = fa }
            }
            d = (currentB - currentA) / (fb - fa)
            currentB = currentA
            fb = fa
            d *= fa
            if (abs(d) < epsilon) {
                Text("La función $f tiene raiz real en $currentA")
                break
            }
            currentA -= d
            fa = Metodo(a = currentA, f = f)
            k++
        }
    }
}



/*

var xiPlus1: Double? = null
    var root: Double? = null
    var contador = 0


for (iteration in 1..200) {
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
 */
