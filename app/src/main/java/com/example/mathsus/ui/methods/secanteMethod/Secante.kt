package com.example.mathsus.ui.methods.secanteMethod

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathsus.R
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .width(380.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
                    text = "k",
                    textColor = Color.White,
                    backgroundColor = colorResource(id = R.color.azulunicauca),
                    fontSize = 12.sp,
                    paddingStart = 6.dp,
                    paddingEnd = 6.dp,
                    paddingTop = 6.dp,
                    paddingBottom = 6.dp,
                    borderColor = Color.Black,
                    borderWidth = 1.dp, // Grosor del borde
                    modifier = Modifier
                        .weight(0.5f)
                        .wrapContentSize(Alignment.Center)
                )
                CurvedBorderText(
                    text = "xk",
                    textColor = Color.White,
                    backgroundColor = colorResource(id = R.color.azulunicauca),
                    fontSize = 12.sp,
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
                    text = "f(xk)",
                    textColor = Color.White,
                    backgroundColor = colorResource(id = R.color.azulunicauca),
                    fontSize = 12.sp,
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
                        text = "$k",
                        textColor = Color.Black, // Color del texto personalizado
                        backgroundColor = colorResource(id = R.color.grisunicauca),
                        fontSize = 10.sp,
                        paddingStart = 6.dp,
                        paddingEnd = 6.dp,
                        paddingTop = 6.dp,
                        paddingBottom = 6.dp,
                        borderColor = Color.Black,
                        borderWidth = 1.dp, // Grosor del borde
                        modifier = Modifier
                            .weight(0.5f)
                            .wrapContentSize(Alignment.Center)
                    )
                    CurvedBorderText(
                        text = currentACoefficient,
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
                        text = "$faCoefficient * 10^$exponentfa ",
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
}


@SuppressLint("DefaultLocale")
@Composable
fun Secante_(a: Double, b: Double, f: String, epsilon: Double, ) {
    var k = 0
    var currentA = a
    var currentB = b
    var fa = Metodo(a = a, f = f)
    var fb = Metodo(a = b, f = f)
    var d: Double

    if (abs(fa) > abs(fb)) {
        currentA = currentB.also { currentB = currentA }
        fa = fb.also { fb = fa }
    }
    for (iteration in 1..200) {
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