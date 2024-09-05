package com.example.mathsus.ui.features.nav_menu_bisection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mathsus.R
import com.example.mathsus.ui.features.BottomNavBarBisection
import com.example.mathsus.ui.features.nav_menu_secante.Drawer
import com.example.mathsus.ui.features.nav_menu_secante.TopBar
import com.example.mathsus.ui.methods.Destinos

@Composable
fun ExerciseBisection(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Destinos.Pantalla1,
        Destinos.Pantalla2,
        Destinos.Pantalla3,
        Destinos.Pantalla4
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Drawer(menuItems = navigationItems, navController = navController)
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar("Ejercicio de cómputo", scope, drawerState) },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        ExerBisection()
                    }
                }
            },
            bottomBar = { BottomNavBarBisection(navController = navController) },
        )
    }
}

@Composable
fun ExerBisection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "1. Usando el método de bisección, determine el punto de intersección de las curvas dadas por y = x^3 – 2x + 1 y y = x^2",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "2. Encuentre una raíz de la siguiente ecuación en el intervalo [0, 1] usando el método de bisección: 9x^4 + 18x^3 + 38x^2 – 57x + 14 = 0.",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "3. Encuentre una raíz de la ecuación tan x = x en el intervalo [4, 5] usando el método de bisección. ¿Qué sucede en el intervalo [1, 2]?",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "4. Encuentre una raíz de la ecuación 6(e^x – x) = 6 + 3x^2 + 2x^3 entre –1 y +1 usando el método de bisección",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "5. Use el método de bisección para encontrar un cero de la ecuación λ cosh(50/λ) = λ + 10 con que empieza este capítulo.",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "6. Use el método de bisección para determinar raíces de estas funciones en los intervalos indicados.",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.exer_cero_biseccion), // Recurso drawable
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Encuentre cada raíz con precisión total de máquina. Use el número correcto de pasos, al menos aproximadamente.",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "7. Pruebe las tres rutinas de bisección en f (x) = x^3 + 2x^2 + 10x – 20, con a = 1 y b = 2. El cero es 1.36880 8108. Al programar esta función polinomial, use multiplicación anidada",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "8. Encuentre las raíces para f(x) = x^3 – 3x + 1 en [0, 1] y g(x) = x^3 – sen x en [0.5, 2]",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}