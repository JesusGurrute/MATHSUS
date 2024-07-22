package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mathsus.ui.methods.secanteMethod.BodySecante

@Composable
fun SecanteScreen(navController: NavHostController) {

    androidx.compose.material.Scaffold(

        topBar = {
            HomeHeader(
                "Método de la secante",
                "Este método imita el de Newton pero evita el cálculo de derivadas.\n" +
                        "Las expresiones que puede usar son: sen(x), cos(x), tan(x), cot(x), sec(x), csc(x), log(x), ln(x), x^,e, pi, sqrt(x) y composición de estas teniendo en cuenta el uso correcto de los paréntesis con puntuacion en decimales en vez de comas."
            )
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    BodySecante()
                }
            }
        },
        bottomBar = { BottomNavBarSecante(navController = navController) },
    )
}

@Composable
fun BottomNavBarSecante(navController: NavController) {
    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("splash") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Calculate, contentDescription = "Calculadora") },
            label = { Text("Calculadora") },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("secante") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Información") },
            label = { Text("Método") },
            selected = false,
            onClick = { navController.navigate("infoSecante") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.LocalLibrary, contentDescription = "") },
            label = { Text("Ejercicios") },
            selected = false,
            onClick = { navController.navigate("exerciseSecante") }
        )
    }
}