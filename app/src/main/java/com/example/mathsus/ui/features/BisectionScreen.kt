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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mathsus.ui.methods.bisectionMethod.BodyBisection

@Composable
fun BisectionScreen(navController: NavHostController) {

    Scaffold(

        topBar = {
            HomeHeader(
                "Método de la bisección",
                "Este método requiere un intervalo [a,b] donde la función sea continua y exista un cambio de signo.\n" +
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
                    BodyBisection()
                }
            }
        },
        bottomBar = { BottomNavBarBisection(navController = navController) },
    )
}

@Composable
fun BottomNavBarBisection(navController: NavController) {
    NavigationBar(
        containerColor = Color.White, // Cambia backgroundColor por containerColor
        tonalElevation = 8.dp // Cambia elevation por tonalElevation
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("splash") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Calculadora") },
            label = { Text("Calculadora") },
            selected = false,
            onClick = { navController.navigate("bisection") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Información") },
            label = { Text("Método") },
            selected = false,
            onClick = { navController.navigate("infoBisection") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Edit, contentDescription = "Ejercicios") },
            label = { Text("Ejercicios") },
            selected = false,
            onClick = { navController.navigate("exerciseBisection") }
        )
    }
}

/*

//Ejemplo para usar un LazyColumn

LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                item {
                    BodyBisection()
                }
                item {
                    CurrenciesSection()
                }
            }


        ---------------------------------------------------------------
Column {
        HomeHeader(
            "Método de la bisección",
            "Este método requiere un intervalo [a,b] donde la función sea continua y exista un cambio de signo.\n" +
                    "Las expresiones que puede usar son: sen(x), cos(x), tan(x), cot(x), sec(x), csc(x), log(x), ln(x), x^,e, pi, sqrt(x) y composición de estas teniendo en cuenta el uso correcto de los paréntesis con puntuacion en decimales en vez de comas."
        )
        Button(
            onClick = {
                navController.navigate("splash")
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Regreso a la pantalla principal")
        }

        BodyBisection()
    }
 */