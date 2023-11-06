package com.sangmeebee.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private val _tasks = WellnessTask.getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask> get() = _tasks

    fun remove(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { task -> task.id == item.id }?.let { task -> task.checked = checked }

}