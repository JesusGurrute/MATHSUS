package com.example.mathsus.ui.features
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SecanteScreen(navController: NavHostController) {
    Column {
        Text(
            text = "Esta es la pantalla para la secante",
            fontSize = 50.sp,
            lineHeight = 116.sp
        )

        Button(
            onClick = {
                navController.navigate("bisection")
        }
        ) {
            Text(text = "Vé a la pantalla de la bisección")
        }
    }
}