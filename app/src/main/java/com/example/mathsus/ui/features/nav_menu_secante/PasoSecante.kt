package com.example.mathsus.ui.features.nav_menu_secante

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mathsus.R
import com.example.mathsus.ui.features.BottomNavBarSecante
import com.example.mathsus.ui.methods.Destinos
import com.example.mathsus.ui.methods.secanteMethod.BodySecante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PasoSecante(navController: NavHostController) {
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
                Drawer(menu_items = navigationItems, navController = navController)
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar(scope, drawerState) },
            content = { padding ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        PasosDeLaSecante()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp) // Ajusta la altura según sea necesario
                    ) {
                        BodySecante()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp) // Ajusta la altura según sea necesario
                    ) {
                        ExplicacionSecante()
                    }
                }
            },
            bottomBar = { BottomNavBarSecante(navController = navController) }
        )
    }
}
@Composable
fun Drawer(menu_items: List<Destinos>, navController: NavHostController) {
    Column {
        Image(
            painterResource(id = R.drawable.menu_lateral),
            contentDescription = "Menú de opciones",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        menu_items.forEach { item ->
            DrawerItem(item = item, navController = navController)
        }
    }
}

@Composable
fun DrawerItem(item: Destinos, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navController.navigate(item.ruta) {
                    // Opcional: Configura el comportamiento de la navegación
                    launchSingleTop = true
                }
            }
            .padding(8.dp)
    ) {
        Image(
            painterResource(id = item.icon),
            contentDescription = item.title,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    TopAppBar(
        title = { Text(text = "Secante paso a paso") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Inicio de Menú")
            }
        })
}

@Composable
fun ExplicacionSecante() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Desde el punto de vista geométrico, el método se basa en aproximar la función:",
                textAlign = TextAlign.Justify
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "y = f(x)",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "por una recta secante a la gráfica de f, y usar la raíz de la recta como aproximación a la solución del problema f(x) = 0.",
                textAlign = TextAlign.Justify
            )
        }


    }
}

@Composable
fun PasosDeLaSecante() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Pasos del método",
                color = Color.Black, // Cambia el color a negro
                style = TextStyle(
                    fontSize = 20.sp, // Ajusta el tamaño según lo necesario
                    fontWeight = FontWeight.Bold, // Aplica negrilla
                    color = Color.Black // Asegura que el color es negro
                ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "1. Para comenzar, fijamos dos valores iniciales denotados como x0 y x1.",
                textAlign = TextAlign.Justify
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "2. Con esos dos puntos se traza la recta que es secante a f en (x0 , f(x0)) y (x1 , f(x1)), siendo f la función ingresada.",
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ec0_pasosecante), // Recurso drawable
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "3. Se halla el punto de corte (x2) de esta recta con el eje x (y = 0) y se despeja,",
                textAlign = TextAlign.Justify
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ec1_pasosecante), // Recurso drawable
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}


/*
tittle = { Text(text = "Método de la secante paso a paso") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Inicio de Menú")
            }
        }
 */