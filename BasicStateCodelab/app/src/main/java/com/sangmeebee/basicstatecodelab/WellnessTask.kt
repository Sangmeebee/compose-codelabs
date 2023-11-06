package com.sangmeebee.basicstatecodelab

data class WellnessTask(
    val id: Int,
    val label: String,
) {
    companion object {
        fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
    }
}