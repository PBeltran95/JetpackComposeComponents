package com.example.jetpackcomponentscatalog

data class CheckInfo(
    val title: String, var selected: Boolean = false, var onCheckedChange: (Boolean) -> Unit
)
