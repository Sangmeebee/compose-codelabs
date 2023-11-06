package com.sangmeebee.basicstatecodelab

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class WellnessTask(
    val id: Int,
    val label: String,
    val initialChecked: Boolean = false
) {
    var checked by mutableStateOf(initialChecked)

    companion object {
        fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
    }
}