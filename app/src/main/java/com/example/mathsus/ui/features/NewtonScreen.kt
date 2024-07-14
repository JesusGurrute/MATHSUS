package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mathsus.ui.features.navigation_menu_newton.ExerciseNewton
import com.example.mathsus.ui.features.navigation_menu_newton.InformationNewton
import com.example.mathsus.ui.methods.FAB
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
                /*
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    CurrenciesSection()
                }
                 */

            }
        },
        bottomBar = { BottomNavBar(navController = navController) },

        //floatingActionButton = { FAB(navController = navController) }

    )

    /*
    // Navegación
    NavHost(
        navController = navController,
        startDestination = "newton"
    ) {
        composable("newton") { NewtonScreen(navController = navController) }
        composable("infoNewton") { InformationNewton(navController = navController) } // Asegúrate de que InfoScreen exista
        composable("exerciseNewton") { ExerciseNewton(navController = navController) } // Asegúrate de que ExerciseScreen exista
    }

     */


}

/*
Button(
            onClick = {
                navController.navigate("splash")
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Regreso a la pantalla principal")
        }
 */
