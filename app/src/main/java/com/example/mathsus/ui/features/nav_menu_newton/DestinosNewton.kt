package com.example.mathsus.ui.features.nav_menu_newton

import com.example.mathsus.R

sealed class DestinosNewton(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    data object Home: DestinosNewton(R.drawable.ic_home, "Inicio", "splash")
    data object CalculateNewtonScreen: DestinosNewton(R.drawable.icon_calculadora, "Método", "Newton")
    data object StepNewtonScreen: DestinosNewton(R.drawable.icon_calculadora, "Newton - Raphson paso a  paso", "pasoNewton")
    data object InfoNewtonScreen: DestinosNewton(R.drawable.ic_ejercicio, "Teoria", "infoNewton")
    data object ExcersiceNewtonScreen: DestinosNewton(R.drawable.ic_ejercicio, "Ejercicios", "exerciseNewton")
}
