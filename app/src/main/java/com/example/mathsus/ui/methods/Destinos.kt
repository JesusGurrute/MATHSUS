package com.example.mathsus.ui.methods

import com.example.mathsus.R

sealed class Destinos(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object Pantalla1: Destinos(R.drawable.ic_home, "Inicio", "splash")
    object Pantalla2: Destinos(R.drawable.icon_calculadora, "Método", "secante")
    object Pantalla3: Destinos(R.drawable.ic_teoria, "Teoria", "infoSecante")
    object Pantalla4: Destinos(R.drawable.ic_ejercicio, "Ejercicios", "exerciseSecante")
}

