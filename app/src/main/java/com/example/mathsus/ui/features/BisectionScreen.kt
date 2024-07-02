package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mathsus.ui.methods.bisectionMethod.BodyBisection

@Composable
fun BisectionScreen(navController: NavHostController) {
    Column (
        //modifier = Modifier.fillMaxWidth()
    ){
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
}

