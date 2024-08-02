package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.*
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mathsus.ui.methods.secanteMethod.BodySecante

@Composable
fun SecanteScreen(navController: NavHostController) {

    Scaffold(

        topBar = {
            HomeHeader(
                "Método de la secante",
                "Este método imita el de Newton pero evita el cálculo de derivadas.\n" +
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
                    BodySecante()
                }
            }
        },
        bottomBar = { BottomNavBarSecante(navController = navController) },
    )
}

@Composable
fun BottomNavBarSecante(navController: NavController) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("splash") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Método") },
            label = { Text("Método") },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("secante") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Paso a paso") },
            label = { Text("Paso a paso") },
            selected = false,
            onClick = { navController.navigate("pasoSecante") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Información") },
            label = { Text("Info") },
            selected = false,
            onClick = { navController.navigate("infoSecante") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Menu, contentDescription = "Ejercicios") },
            label = { Text("Practica") },
            selected = false,
            onClick = { navController.navigate("exerciseSecante") }
        )
    }
}