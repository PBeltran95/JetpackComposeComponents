package com.example.jetpackcomponentscatalog

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun MyScaffold() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        MyTopAppBar {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Pulsaste $it")
            }
        }
    }, scaffoldState = scaffoldState) {

    }
}

@Composable
fun MyTopAppBar(onIconClick: (String) -> Unit) {
    TopAppBar(
        title = { Text(text = "Toolbar personalizada") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        navigationIcon = { IconButton(onClick = { onIconClick("Atras") }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
        } },
        actions = {
            IconButton(onClick = { onIconClick("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}