package com.example.jetpackcomponentscatalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MainViewModel : ViewModel(){

    private var mutableInitialValue = false
    private val _mutable = MutableLiveData(mutableInitialValue)
    val observable: Flow<Boolean> get() = flowOf(mutableInitialValue)

    fun modifyMutable() {
        mutableInitialValue = !mutableInitialValue
    }
}