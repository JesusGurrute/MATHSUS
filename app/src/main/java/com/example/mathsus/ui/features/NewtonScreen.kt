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
import com.example.mathsus.ui.methods.newtonMethod.BodyNewtonRaphson

@Composable
fun NewtonScreen(navController: NavHostController) {

    androidx.compose.material.Scaffold(

        topBar = {
            HomeHeader(
                "Método de Newton-Raphson",
                "En el método de Newton, se supone desde el principio que la función f es derivable. Esto implica que la gráfica de f tiene una pendiente definida en cada punto y, por tanto, una recta tangente única.\n" +
                        "\nUse paréntesis para agrupar operaciones: (a + b) * (c + d), evite espacios inecesarios, use punto décimal y no la coma, use constantes definidas como pi (phi) o e (número áureo) y use funciones trigonométricas con sus nombres en inglés: sin(x) y no sen(x)."
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
                    BodyNewtonRaphson()
                }
            }
        },
        bottomBar = { BottomNavBarNewton(navController = navController) },
    )
}


@Composable
fun BottomNavBarNewton(navController: NavController) {
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
            onClick = { navController.navigate("newton") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Información") },
            label = { Text("Método") },
            selected = false,
            onClick = { navController.navigate("infoNewton") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.LocalLibrary, contentDescription = "") },
            label = { Text("Ejercicios") },
            selected = false,
            onClick = { navController.navigate("exerciseNewton") }
        )
    }
}
