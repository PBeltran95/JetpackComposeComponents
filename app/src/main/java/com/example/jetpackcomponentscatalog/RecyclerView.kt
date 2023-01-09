@file:OptIn(ExperimentalFoundationApi::class)

package com.example.jetpackcomponentscatalog

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomponentscatalog.model.SuperHero
import kotlinx.coroutines.launch

@Composable
fun SimpleRecyclerView() {
    val myList = listOf("Pablo", "Pepe", "Jaime")
    LazyColumn {
        item { Text(text = "Header") }
        items(myList) { Text(text = "Hola mi nombre es: $it") }
        item { Text(text = "Footer") }
    }
}

@Composable
fun SuperHeroStickyView() {
    val context = LocalContext.current
    val superHeroes: Map<Char, List<SuperHero>> = getSuperHeroes().sortedBy { it.superHeroName.first() }.groupBy { it.superHeroName.first() }

    LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)) {
        superHeroes.forEach { (letter, superHeroName) ->
            stickyHeader { Text(text = letter.toString(), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.fillMaxWidth()) }
            items(superHeroName) { superHero ->
                ItemHero(superHero) { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
            }
        }
    }
}

@Composable
fun SuperHeroViewSpecialControl() {
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    Column {
        LazyColumn(
            state = rvState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(getSuperHeroes()) { superHero ->
                ItemHero(superHero) { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
            }
        }

        val showButton by remember { derivedStateOf { rvState.firstVisibleItemIndex > 0 } }
        var animationState by remember { mutableStateOf(showButton) }
        val coroutineScope = rememberCoroutineScope()

        //Este Atributo me indica si el scroll es hacia arriba o hacia abajo
        rvState.firstVisibleItemScrollOffset

        AnimatedVisibility(visible = showButton) {
            Button(onClick = { coroutineScope.launch { rvState.animateScrollToItem(0) } }, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Text(text = "Soy un boton")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperHeroGridView() {
    val context = LocalContext.current
    LazyVerticalGrid(cells = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(2.dp), content = {
        items(getSuperHeroes()) { superHero ->
            ItemHero(superHero) { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
        }
    }, contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp))
}

@Composable
fun SuperHeroView() {
    val context = LocalContext.current
    LazyRow (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getSuperHeroes()) { superHero ->
            ItemHero(superHero) { Toast.makeText(context, it.realName, Toast.LENGTH_SHORT).show() }
        }
    }
}

@Composable
fun ItemHero(superHero: SuperHero, onItemSelected: (SuperHero) -> Unit) {
    Card(border = BorderStroke(2.dp, color = Color.Red), modifier = Modifier
        .width(200.dp)
        .clickable {
            onItemSelected(superHero)
        }) {
        Column {
            Image(
                painter = painterResource(id = superHero.drawable),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = superHero.superHeroName,modifier = Modifier.align(Alignment.CenterHorizontally))
            Text(
                text = superHero.realName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 12.sp
            )
            Text(
                text = superHero.publisher,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp),
                fontSize = 10.sp
            )
        }
    }
}

fun getSuperHeroes(): List<SuperHero> {
    return listOf(
        SuperHero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        SuperHero("SuperMan", "Clark Joseph Kent", "DC", R.drawable.ic_launcher_background),
        SuperHero("Wolverine", "James Howlett", "Marvel", R.drawable.logan),
        SuperHero("Batman", "Bruce Wayne", "DC", R.drawable.batman),
        SuperHero("Thor", "Thor Odison", "Marvel", R.drawable.thor),
        SuperHero("Flash", "Jay Garrick", "DC", R.drawable.flash),
        SuperHero("Green Lantern", "Alan Scott", "DC", R.drawable.green_lantern),
        SuperHero("Wonder Woman", "Princess Diana", "DC", R.drawable.wonder_woman)
    )
}