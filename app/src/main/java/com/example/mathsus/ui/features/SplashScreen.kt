package com.example.mathsus.ui.features


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun SplashScreen(navController: NavController) {
    val splashUrl =
        "https://i.imgur.com/gjxRu9X.png"

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = splashUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart)
                .padding(16.dp)
                .background(
                    shape = RoundedCornerShape(32.dp),
                    color = Color.White
                )
                .padding(16.dp)

        ) {
            VerticalButton(
                //vector = Icons.Filled.DataThresholding,
                text = "Método\nde la \nbisección"
            ) { navController.navigate("bisection") }
            VerticalButton(
                //vector = Icons.Filled.Addchart,
                text = "Método\nNewton\nRaphson"
            ) { navController.navigate("newton") }
            VerticalButton(
                //vector = Icons.Filled.DisplaySettings,
                text = "Método\nde la \nsecante"
            ) { navController.navigate("secante") }


        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp)
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White
                )
                .padding(16.dp)
        ) {

        }

    }


}

@Composable
fun VerticalButton(
    //vector: ImageVector,
    text: String,
    onClickAction: () -> Unit
) {
    Button(
        onClick = { onClickAction() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            /*Icon(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                imageVector = vector,
                contentDescription = ""
            )*/
            Text(text = text, fontSize = 15.sp)
            Spacer(modifier = Modifier.width(8.dp))

        }

    }
}



