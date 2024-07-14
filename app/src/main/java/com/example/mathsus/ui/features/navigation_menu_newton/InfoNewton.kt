package com.example.mathsus.ui.features.navigation_menu_newton

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
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mathsus.R
import com.example.mathsus.ui.features.BottomNavBar
import com.example.mathsus.ui.methods.FAB

@Composable
fun InformationNewton(navController: NavHostController) {

    androidx.compose.material.Scaffold(


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
                    InfoNewton(navController = navController)
                }

            }
        },
        bottomBar = { BottomNavBar(navController = navController) },

        //floatingActionButton = { FAB(navController = navController) }
    )
}

@Composable
fun InfoNewton(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Método de Newton",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Interpretaciones del método de Newton",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "En el método de Newton, se supone desde el principio que la función f es derivable. Esto implica que la gráfica de f tiene una pendiente definida en cada punto y, por tanto, una recta tangente única. Ahora permítanos estudiar la siguiente idea simple. En un punto dado (x₀, f(x₀)) en la gráfica de f, hay una tangente, que es una bastante buena aproximación a la curva en la vecindad del punto. Analíticamente, esto significa que la función lineal:",
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ec_cero_newton), // Recurso drawable
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "está cerca de la función dada f cerca de x₀. En x₀ las dos funciones l y f concuerdan. Tomamos el cero de f como una aproximación del cero de l. El cero de l se encuentra fácilmente:",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_uno_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Por esto, empezando con el punto x₀ (al que podemos interpretar como una aproximación a la raíz buscada), pasemos a un nuevo punto x1 obtenido de la fórmula anterior.Naturalmente, el proceso \n" +
                    "se puede repetir (iterando) para producir una sucesión de puntos:",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_dos_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "En condiciones favorables, la sucesión de puntos tenderá a un cero de f.",
            textAlign = TextAlign.Justify
        )

        Text(
            text = "En la siguiente figura se muestra la geometría del método de Newton. La recta y = l(x) es tangente a la curva y = f(x). Ésta interseca el eje x en un punto x1. La pendiente de l(x) es f´(x0).",
            textAlign = TextAlign.Justify
        )


        Image(
            painter = painterResource(id = R.drawable.graph_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Hay otras maneras de interpretar el método de Newton. Suponga de nuevo que x₀ es una aproximación inicial a una raíz de f. Nos preguntamos: ¿qué corrección h se debe sumar a x₀ para obtener la raíz exactamente? Obviamente, queremos",
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "f(x₀+h)=0", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Si f es una función suficientemente bien comportada, se tendrá una serie de Taylor en x₀. Así, podríamos escribir")

        Image(
            painter = painterResource(id = R.drawable.ec_tres_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Por supuesto, no es fácil determinar h a partir de esta ecuación. Por tanto, nos rendimos a la expectativa de llegar a la raíz verdadera en un solo paso y buscamos sólo una aproximación para h. Ésta se puede obtener al despreciar los dos primeros términos en la serie",
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "f(x₀) + hf´(x₀) = 0", Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "La h que resuelve esta no es la h que resuelve f(x₀ + h) = 0, pero es el número que se calcula más fácilmente",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_cuatro_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Nuestra nueva aproximación es entonces",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_cinco_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "y el proceso se puede repetir. En retrospectiva, vemos que después de todo no era necesaria la serie de Taylor, ya que sólo usamos los primeros dos términos. En el análisis que después haremos, se ha supuesto que f´´ es continua en una vecindad de la raíz. Esta suposición nos permite calcular los errores del proceso.\n" +
                    " Si el método de Newton se describe en términos de una sucesión x₀,x1, . . . , entonces se aplica la siguiente definición recursiva o inductiva:",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_seis_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "aturalmente, la pregunta interesante es si",
            textAlign = TextAlign.Justify
        )

        Image(
            painter = painterResource(id = R.drawable.ec_siete_newton), // Recurso drawable
            contentDescription = "Home header background",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "donde r es la raíz deseada.",
            textAlign = TextAlign.Justify
        )

    }


}
