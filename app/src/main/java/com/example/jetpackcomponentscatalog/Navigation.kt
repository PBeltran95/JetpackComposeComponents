package com.example.jetpackcomponentscatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jetpackcomponentscatalog.model.Routes
import com.example.jetpackcomponentscatalog.model.Routes.*

@Composable
fun Screen1(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Pantalla 1")
            Spacer(modifier = Modifier.size(50.dp))
            Button(onClick = { navController.navigate(
                Screen2.route
            ) }) {
                Text(text = "Navegar a la siguiente pantalla")
            }
            Spacer(modifier = Modifier.size(50.dp))
            ColorAnimationSimple()
        }
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .clickable {
                navController.navigate(
                    Screen3.route
                )
            }
    ) {
        Text(text = "Pantalla 2", modifier = Modifier
            .align(Alignment.Center)
            )
    }
}

@Composable
fun Screen3(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .clickable { navController.navigate(Screen4.createRoute(29)) }
    ) {
        Text(
            text = "Pantalla 3",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Screen4(navController: NavHostController, age: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .clickable { navController.navigate("screen5") }
    ) {
        Text(
            text = "Tengo $age años de edad",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Screen5(navController: NavHostController, name: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .clickable { navController.navigate(Screen1.route) }
    ) {
        Text(
            text = "Me llamo $name",
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}