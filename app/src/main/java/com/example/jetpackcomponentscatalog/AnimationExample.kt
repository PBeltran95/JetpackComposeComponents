package com.example.jetpackcomponentscatalog

import android.widget.Space
import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import kotlin.random.Random.Default.nextInt

@Composable
fun ColorAnimationSimple() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        var colorBoolean by rememberSaveable {
            mutableStateOf(false)
        }
        var showBox by rememberSaveable {
            mutableStateOf(true)
        }
        val realColor by animateColorAsState(
            targetValue = if (colorBoolean) Color.Red else Color.Yellow,
            animationSpec = tween(2000),
            finishedListener = { showBox = !showBox }
        )

        if (showBox) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(realColor)
                    .clickable { colorBoolean = !colorBoolean }
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        SizeAnimation()
        Spacer(modifier = Modifier.size(20.dp))
        VisibilityAnimation()
        Spacer(modifier = Modifier.size(20.dp))
        CrossFadeAnimation()
    }
}

@Composable
fun SizeAnimation() {
    var isSmall by rememberSaveable {
        mutableStateOf(true)
    }
    val size by animateDpAsState(
        targetValue = if (isSmall) 50.dp else 100.dp,
        animationSpec = tween(500),
        finishedListener = {  }
    )

    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Red)
            .clickable { isSmall = !isSmall }
    )
}

@Composable
fun VisibilityAnimation() {
    var isVisible by rememberSaveable {
        mutableStateOf(true)
    }

    Column {
        Button(onClick = { isVisible = !isVisible }) {
            Text(text = "Mostrar/Ocultar")
        }
        Spacer(modifier = Modifier.size(10.dp))
        AnimatedVisibility (isVisible,
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally(),
            ) {
            Box(modifier = Modifier
                .size(50.dp)
                .background(Color.Red))
        }
    }
}

@Composable
fun CrossFadeAnimation() {
    var myComponentType: ComponentType by remember {
        mutableStateOf(ComponentType.Text)
    }

    Column {
        Button(onClick = { myComponentType = getRandomComponentType() }) {
            Text(text = "Cambiar componente")
        }
    }

    Crossfade(targetState = myComponentType) { condition ->
        when (condition) {
            ComponentType.Text -> { Text(text = "Soy un text") }
            ComponentType.Image -> { Icon(Icons.Default.Star, contentDescription = "") }
            ComponentType.Box -> { Box(modifier = Modifier.size(50.dp).background(Color.Red)) }
            ComponentType.Error -> { Text(text = "ERRORRR", modifier = Modifier.background(Color.Red)) }
        }
    }
}

fun getRandomComponentType(): ComponentType {

    return when (nextInt(from = 0, until = 3)) {
        0 -> { ComponentType.Text }
        1 -> { ComponentType.Image }
        2 -> { ComponentType.Box }
        3 -> { ComponentType.Error }
        else -> { ComponentType.Error }
    }
}

enum class ComponentType {
    Image, Text, Box, Error
}