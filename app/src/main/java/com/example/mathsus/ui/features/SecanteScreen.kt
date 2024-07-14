package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mathsus.ui.methods.FAB
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
        floatingActionButton = { FAB(navController = navController) }

    )

    /*
    Column {
                Button(
            onClick = {
                navController.navigate("splash")
        }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Regresa a la pantalla principal")
        }

        BodySecante()


    }
     */

}