package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SplashScreen(navController: NavController) {
    Column {
        Text(
            text = "Esta es la pantalla de bienvenida",
            fontSize = 50.sp,
            lineHeight = 116.sp
        )

        Button(
            onClick = {
                navController.navigate("secante")
            }
        ) {
            Text(text = "Vé a la pantalla de secante")
        }
    }
}