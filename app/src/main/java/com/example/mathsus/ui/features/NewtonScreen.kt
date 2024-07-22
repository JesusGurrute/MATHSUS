package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
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
                        "Las expresiones que puede usar son: sen(x), cos(x), tan(x), cot(x), sec(x), csc(x), log(x), ln(x), x^,e, pi, sqrt(x) y composición de estas teniendo en cuenta el uso correcto de los paréntesis con puntuacion en decimales en vez de comas.\n" +
                        "El máximo de iteraciones que usa es de 200"
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

        //floatingActionButton = { FAB(navController = navController) }
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
