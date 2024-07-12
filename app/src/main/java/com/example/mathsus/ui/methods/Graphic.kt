package com.example.mathsus.ui.methods

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

import kotlin.math.min


@Composable
fun FunctionGraph(funcion: String, zoom: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    )
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawFunction(funcion, zoom)
    }

}

fun DrawScope.drawFunction(funcion: String, zoom: Float) {
    val width = size.width
    val height = size.height
    val scale = (min(width, height) / 10f) * zoom

    // Dibujar ejes
    drawLine(
        color = Color.Black,
        start = Offset(0f, height / 2),
        end = Offset(width, height / 2),
        strokeWidth = 2f
    )
    drawLine(
        color = Color.Black,
        start = Offset(width / 2, 0f),
        end = Offset(width / 2, height),
        strokeWidth = 2f
    )

    // Dibujar números en los ejes
    val textPaint = Paint().apply {
        color = android.graphics.Color.BLACK
        textSize = 24f / zoom // Ajustar el tamaño del texto según el zoom
    }

    // Dibujar números en el eje x
    for (i in -5..5) {
        val x = width / 2 + i * scale
        drawContext.canvas.nativeCanvas.apply {
            drawText(i.toString(), x, height / 2 + 24f / zoom, textPaint)
        }
    }

    // Dibujar números en el eje y
    for (i in -5..5) {
        val y = height / 2 - i * scale
        drawContext.canvas.nativeCanvas.apply {
            drawText((-i).toString(), width / 2 + 8f / zoom, y, textPaint)
        }
    }


    // Dibujar la función con clipping
    withTransform({
        clipRect(0f, 0f, width, height)
    }) {
        for (x in -1000..1000) {
            val x1 = x / 100f
            val y1 = calcularFuncion(x1.toDouble(), funcion).toFloat()
            val x2 = (x + 1) / 100f
            val y2 = calcularFuncion(x2.toDouble(), funcion).toFloat()

            drawLine(
                color = Color.Blue,
                start = Offset(
                    width / 2 + x1 * scale,
                    height / 2 - y1 * scale
                ),
                end = Offset(
                    width / 2 + x2 * scale,
                    height / 2 - y2 * scale
                ),
                strokeWidth = 2f
            )
        }
    }
}


fun encontrarInterseccion(funcion: String): Double? {
    // Búsqueda de la intersección con el eje X
    val step = 0.01
    for (x in -500..500) {
        val x1 = x / 100.0
        val y1 = calcularFuncion(x1, funcion)
        if (y1 == 0.0) {
            return x1
        }
        val x2 = (x + 1) / 100.0
        val y2 = calcularFuncion(x2, funcion)
        if (y1 * y2 < 0) { // Cambio de signo, hay una intersección
            // Aproximación más precisa
            return (x1 + x2) / 2
        }
    }
    return null
}

