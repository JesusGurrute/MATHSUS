package com.example.mathsus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mathsus.ui.features.BisectionScreen
import com.example.mathsus.ui.features.Info
import com.example.mathsus.ui.features.NewtonScreen
import com.example.mathsus.ui.features.SecanteScreen
import com.example.mathsus.ui.features.SplashScreen
import com.example.mathsus.ui.theme.MATHSUSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MATHSUSTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {

                        composable(route = "splash") {
                            SplashScreen(navController = navController)
                        }

                        composable(route = "bisection") {
                            BisectionScreen(navController = navController)
                        }

                        composable(route = "newton") {
                            NewtonScreen(navController = navController)
                        }

                        composable(route = "secante") {
                            SecanteScreen(navController = navController)
                        }

                        composable(route = "info") {
                            Info(navController = navController)                        }
                    }

                }


            }
        }
    }
}

