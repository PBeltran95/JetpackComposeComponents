package com.example.jetpackcomponentscatalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun MyScaffold() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MyTopAppBar(onIconClick = { coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Pulsaste $it")
            } }, onDrawerClick = { coroutineScope.launch { scaffoldState.drawerState.open() } })
        },
        scaffoldState = scaffoldState,
        bottomBar = { MyBottomBar() },
        floatingActionButton = { MyFab() },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = false,
        drawerContent = { MyNavigationDrawer(onCloseDrawer = { coroutineScope.launch { scaffoldState.drawerState.close() } }) },
        drawerGesturesEnabled = true
    ) {

    }
}

@Composable
fun MyTopAppBar(onIconClick: (String) -> Unit, onDrawerClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Toolbar personalizada") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        navigationIcon = { IconButton(onClick = { onDrawerClick() }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        } },
        actions = {
            IconButton(onClick = { onIconClick("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
fun MyBottomBar() {
    var index by remember { mutableStateOf(0) }
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        BottomNavigationItem(selected = index == 0, onClick = { index = 0 }, icon = {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "1")
        }, label = { Text(text = "Home") })
        BottomNavigationItem(selected = index == 1, onClick = { index = 1 }, icon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "1")
        }, label = { Text(text = "Search") })
        BottomNavigationItem(selected = index == 2, onClick = { index = 2 }, icon = {
            Icon(imageVector = Icons.Filled.Star, contentDescription = "1")
        }, label = { Text(text = "Favorites") })
    }
}

@Composable
fun MyFab() {
    FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color.Yellow, contentColor = Color.Black) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun MyNavigationDrawer(onCloseDrawer: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "Opcion 1",
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(text = "Opcion 2",
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
        Text(text = "Opcion 3",
            modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
                .clickable { onCloseDrawer() }
        )
    }
}