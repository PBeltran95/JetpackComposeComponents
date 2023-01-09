package com.example.jetpackcomponentscatalog.model

import androidx.annotation.DrawableRes

data class SuperHero(
    val superHeroName: String,
    val realName: String,
    val publisher: String,
    @DrawableRes val drawable: Int
)
