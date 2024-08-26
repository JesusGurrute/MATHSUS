package com.example.mathsus.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.*
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mathsus.R
import com.example.mathsus.ui.features.nav_menu_secante.Drawer
import com.example.mathsus.ui.features.nav_menu_secante.TopBar
import com.example.mathsus.ui.methods.Destinos
import com.example.mathsus.ui.methods.secanteMethod.BodySecante

@Composable
fun SecanteScreen(navController: NavHostController) {
    val splashUrl = "https://i.imgur.com/gjxRu9X.png"
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
            topBar = { TopBar("Método de la secante", scope, drawerState) },

            content = { padding ->

                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberAsyncImagePainter(model = splashUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(450.dp) // Puedes ajustar la altura según sea necesario
                            ) {
                                BodySecante()
                            }
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                UsoSecante()
                            }
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                ExplicacionSecante()
                            }
                        }
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {

                            }
                        }

                    }
                }
            },
            bottomBar = { BottomNavBarSecante(navController = navController) },
        )

    }

}

@Composable
fun BottomNavBarSecante(navController: NavController) {
    NavigationBar(
        modifier = Modifier.navigationBarsPadding(), // Añadir padding para evitar superposición con la barra de navegación
        containerColor = colorResource(id = R.color.azulunicauca),
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio", tint = Color.White) },
            label = { Text("Inicio", color = Color.White) },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("splash") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "Método",
                    tint = Color.White
                )
            },
            label = { Text("Método", color = Color.White) },
            selected = false, // Cambia esto según la navegación actual
            onClick = { navController.navigate("secante") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Paso a paso", tint = Color.White
                )
            },
            label = { Text("Paso paso", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("pasoSecante") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Info,
                    contentDescription = "Información",
                    tint = Color.White
                )
            },
            label = { Text("Info", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("infoSecante") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Ejercicios",
                    tint = Color.White
                )
            },
            label = { Text("Práctica", color = Color.White) },
            selected = false,
            onClick = { navController.navigate("exerciseSecante") }
        )
    }
}


@Composable
fun ExplicacionSecante() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(10.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            )
            .navigationBarsPadding()
            .padding(10.dp)
    ) {
        Text(
            text = "Graficamente",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.rojounicauca),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Desde el punto de vista geométrico, el método se basa en aproximar la función:",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "y = f(x)",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "por una recta secante a la gráfica de f, y usar la raíz de la recta como aproximación a la solución del problema f(x) = 0.",
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun UsoSecante() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(10.dp)
            .background(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            )
            .navigationBarsPadding()
            .padding(10.dp)
    ) {
        Text(
            text = "Ejemplo de una función bien formada:",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "f(x) = 2 * sin(x) + log(x, 10) - 3*x^2 + pi",
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tenga en cuenta",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.rojounicauca),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Usa paréntesis para agrupar operaciones:\n " +
                    "(2 + 3) * 4\n" +
                    "• Operadores estándar: +, -, *, /, ^(potencia)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Asegúrate de cerrar todos los paréntesis"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Usa el punto decimal, no la coma"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "• Usa las funciones sin(), cos(), tan(), log(), etc. con sus nombres en inglés: sin(x),  y no  sen(x)"
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "• Puedes usar constantes predefinidas como pi, e, [phi] (número áureo)")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "• Siempre prueba tus funciones con valores conocidos para verificar su corrección")
    }
}

/*
topBar = {
            HomeHeader(
                "Método de la secante",
                "Este método imita el de Newton pero evita el cálculo de derivadas.\n" +
                        "\nUse paréntesis para agrupar operaciones: (a + b) * (c + d), evite espacios inecesarios, use punto décimal y no la coma, use constantes definidas como pi (phi) o e (número áureo) y use funciones trigonométricas con sus nombres en inglés: sin(x) y no sen(x)."
            )
        },
 */