package com.example.mathsus.ui.methods.bisectionMethod

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mathsus.ui.methods.Metodo
import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions.abs
import kotlin.math.sign

@SuppressLint("DefaultLocale")
@Composable
fun Bisection(
    f: String,
    a: Double,
    b: Double,
    epsilon: Double,
) {

    var k = 0
    var fa = Metodo(a = a, f = f)
    var fb = Metodo(a = b, f = f)
    var error: Double
    var c: Double
    var fc: Double
    var currentA = a
    var currentB = b

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(text = " k ")
                Text(text = "        c_k       ")
                Text(text = "                f(c_k)     ")
                Text(text = "                  error     ")
            }

            if (fa.sign == fb.sign) {
                Toast.makeText(
                    context,
                    "La función tiene el mismo signo en a y en b",
                    Toast.LENGTH_SHORT
                ).show()
            }

            error = currentB - currentA

            while (k <= 200) {
                error /= 2
                c = currentA + error
                fc = Metodo(a = c, f = f)

                val roundc = String.format("%.4f", c)

                val roundfc = String.format("%.4e", fc)
                val parts = roundfc.split("e")
                val coefficient = parts[0].toDouble()
                val exponent = parts[1].toInt()
                // Quitar ceros adicionales si es necesario
                val trimmedCoefficient = if (coefficient % 1 == 0.0) {
                    coefficient.toInt().toString()
                } else {
                    parts[0].replace(Regex("0*$"), "")
                }

                val rounderror = String.format("%.4e", error)
                val partserror = rounderror.split("e")
                val coefficienterror = partserror[0].toDouble()
                val exponenterror = partserror[1].toInt()
                val errorCoefficient = if (coefficienterror % 1 == 0.0) {
                    coefficienterror.toInt().toString()
                } else {
                    partserror[0].replace(Regex("0*$"), "")
                }


                Row {
                    Text(text = " $k ")
                    Text(text = "     $roundc      ")
                    Text(text = "          $trimmedCoefficient * 10^$exponent      ")
                    Text(text = "          $errorCoefficient * 10^$exponenterror     ")
                }

                if (abs(error) < epsilon) {
                    Text("La función $f tiene raiz en $roundc")
                    return
                }

                if (fa.sign != Metodo(a = c, f = f).sign) {
                    currentB = c
                    fb = Metodo(a = c, f = f)
                } else {
                    currentA = c
                    fa = Metodo(a = c, f = f)
                }

                k++
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProofBisection() {
    Bisection(f = "x^10-1", a = 0.0, b = 1.4, epsilon = 0.001)
}


/*
if (f_a.sign == f_b.sign) {
            // "la función tiene el mismo signo en a y en b”"
        } else {
            "Los números tienen signos diferentes"
        }
 */


/*
    for (iteration in 1..200) {
            contador++

            val round_a = String.format("%.4f", a)
            val round_b = String.format("%.4f", b)

            c = (a + b) / 2
            val round_c = String.format("%.4f", c)


            val roundfxi_1 = String.format("%.4f", fa)
            val f_c = Metodo(a = c!!, f = f)
            val roundfxr = String.format("%.4f", f_c)

            var fxi_1fxr = fa * f_c
            val roundfxi_1fxr = String.format("%.4f", fxi_1fxr)


            error = abs((c!! - b) / c!!) * 100

            val rounderror = String.format("%.4f", error)
            Row {
                Text(text = " $contador ")
                Text(text = "    $round_a      ")
                //Text(text = "     $roundxi    ")
                Text(text = "          $round_c     ")
                //Text(text = "        $roundfxi_1     ")
                //Text(text = "       $roundfxr     ")
                //Text(text = "      $roundfxi_1fxr    ")
                //Text(text = "      $rounderror     ")
            }
            if (Math.abs(c!! - b) < epsilon.toDouble()) {
                root = c
                break
            }

            if (fxi_1fxr < 0) {
                val a = a
                val b = c as Double
            } else {
                val a = c as Double
                val b = b
            }

        }
 */