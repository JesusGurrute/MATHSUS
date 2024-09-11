package com.example.mathsus.ui.features.nav_menu_newton

data class ResultadoNewton(
    val iteracion: Int,
    val x: Double,
    val fx: Double,
    val dfx: Double,
    val nextX: Double,
    val symbolicDerivative: String
)


