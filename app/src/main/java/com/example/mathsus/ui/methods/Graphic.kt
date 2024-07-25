package com.example.mathsus.ui.methods

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

import kotlin.math.min

//fun FunctionGraph(funcion: String, zoom: Float)
@SuppressLint("AutoboxingStateCreation")
@Composable
fun FunctionGraph(
    funcion: String,
    initialZoom: Float = 1f,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier  // Añadimos este parámetro
) {
    var scale by remember { mutableStateOf(initialZoom) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        scale *= zoomChange
        offset += offsetChange
    }

    Box(
        modifier = modifier  // Usamos el modifier aquí
            .fillMaxSize()
            .transformable(state = state)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            drawFunction(funcion, scale, offset)
        }
    }
}

fun DrawScope.drawFunction(funcion: String, zoom: Float, offset: Offset) {
    val width = size.width
    val height = size.height
    val baseScale = min(width, height) / 10f

    withTransform({
        translate(offset.x, offset.y)
        scale(zoom, zoom, Offset(width / 2, height / 2))
    }) {
        // Dibujar ejes
        drawLine(Color.Black, Offset(0f, height / 2), Offset(width, height / 2), 2f)
        drawLine(Color.Black, Offset(width / 2, 0f), Offset(width / 2, height), 2f)

        // Dibujar números en los ejes
        val textPaint = android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            textSize = 24f / zoom
        }

        // Dibujar números en el eje x
        for (i in -5..5) {
            val x = width / 2 + i * baseScale
            drawContext.canvas.nativeCanvas.drawText(
                i.toString(),
                x,
                height / 2 + 24f / zoom,
                textPaint
            )
        }

        // Dibujar números en el eje y
        for (i in -5..5) {
            val y = height / 2 - i * baseScale
            drawContext.canvas.nativeCanvas.drawText(
                (-i).toString(),
                width / 2 + 8f / zoom,
                y,
                textPaint
            )
        }

        // Dibujar la función con clipping
        clipRect(0f, 0f, width, height) {
            try {
                var lastX: Float? = null
                var lastY: Float? = null
                for (x in -1000..1000) {
                    val xVal = x / 100f
                    val yVal = calcularFuncion(xVal.toDouble(), funcion).toFloat()

                    if (yVal.isFinite()) {
                        val currentX = width / 2 + xVal * baseScale
                        val currentY = height / 2 - yVal * baseScale

                        if (lastX != null && lastY != null) {
                            drawLine(
                                Color.Blue,
                                Offset(lastX, lastY),
                                Offset(currentX, currentY),
                                2f
                            )
                        }

                        lastX = currentX
                        lastY = currentY
                    } else {
                        lastX = null
                        lastY = null
                    }
                }
            } catch (e: Exception) {
                // Manejar error en caso de función inválida
                drawContext.canvas.nativeCanvas.drawText(
                    "Error: Función inválida",
                    width / 4,
                    height / 2,
                    textPaint
                )
            }
        }
    }
}
