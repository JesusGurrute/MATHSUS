package com.example.mathsus.ui.methods.bisectionMethod

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mathsus.ui.methods.Metodo
import org.mariuszgromada.math.mxparser.mathcollection.MathFunctions.abs

@SuppressLint("DefaultLocale")
@Composable
fun Bisection(
    f: String,
    a: Double,
    b: Double,
    epsilon: Double,
    //niter: Int
) {
    var a = a.toDouble()
    var b = b.toDouble()
    var c: Double? = null
    var contador = 0
    var error: Double
    var root: Double? = null
// var niter = niter

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    )
    Column {
        Row {
            Text(text = " k ")
            Text(text = "          a       ")
            //Text(text = "          xb     ")
            Text(text = "                b     ")
            //Text(text = "                  f(xa)     ")
            //Text(text = "           f(xr)     ")
            //Text(text = "          f(xa)f(xr)     ")
            //Text(text = "          Ea     ")
        }

        for (iteration in 1..200) {
            contador++

            val round_a = String.format("%.4f", a)
            val round_b = String.format("%.4f", b)

            c = (a + b) / 2
            val round_c = String.format("%.4f", c)

            val f_a = Metodo(a = a, f = f)
            val roundfxi_1 = String.format("%.4f", f_a)
            val f_c = Metodo(a = c!!, f = f)
            val roundfxr = String.format("%.4f", f_c)

            var fxi_1fxr = f_a * f_c
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
                a = a
                b = c as Double
            } else {
                a = c as Double
                b = b
            }

        }
        Text(text = "La raís es $c")
    }

}

@Composable
fun ProofBisection() {
    Bisection(f = "x^10-1", a = 0.0, b = 1.4, epsilon = 0.001)
}