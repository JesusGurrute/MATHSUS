package com.example.mathsus.ui.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mathsus.ui.features.navigation_menu_newton.InformationNewton
import com.example.mathsus.ui.methods.FAB


@Composable
fun Info(
    navController: NavHostController

) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

            }

        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())

                ) {
                    InformationNewton(navController = navController)
                }
            }
        },
        floatingActionButton = { FAB(navController = navController) }

    )

}
