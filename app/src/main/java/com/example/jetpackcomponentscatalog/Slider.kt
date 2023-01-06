package com.example.jetpackcomponentscatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment

@Composable
fun BasicSlider() {
    var sliderPosition by rememberSaveable { mutableStateOf(0f) }
    var completeValue by rememberSaveable { mutableStateOf("0.0") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = { completeValue = sliderPosition.toString()},
            valueRange = 0f..10f,
            steps = 9
        )
        Text(text = completeValue)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyRangeSlider() {
    var currentRange by remember { mutableStateOf(0f..10f) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RangeSlider(
            values = currentRange,
            onValueChange = {currentRange = it},
            valueRange = 0f..40f,
            steps = 9
        )
        Text(text = "Valor inferior ${currentRange.start}")
        Text(text = "Valor superior ${currentRange.endInclusive}")
    }
}