package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.mathsus.ui.methods.bisectionMethod.BodyBisection

@Composable
fun BisectionScreen() {
    Column (
        //modifier = Modifier.fillMaxWidth()
    ){
        HomeHeader(
            "Método de la bisección",
            "Este método requiere un intervalo [a,b] donde la función sea continua y exista un cambio de signo. Las expresiones que puede usar son: sen(x), cos(x), tan(x), cot(x), sec(x), csc(x), log(x), ln(x), x^,e, pi, sqrt(x) y composición de estas teniendo en cuenta el uso correcto de los paréntesis con puntuacion en decimales en vez de comas."
        )

        BodyBisection()


       /*
        Button(

            onClick = {
                navController.navigate("newton")
            }
        ) {
            Text(text = "Vé a la pantalla de Newton")
        }

        */
    }
}

