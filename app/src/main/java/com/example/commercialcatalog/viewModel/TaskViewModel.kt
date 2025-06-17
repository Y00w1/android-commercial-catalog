package com.example.commercialcatalog.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.commercialcatalog.model.Task
import com.example.commercialcatalog.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _notificationEvent = MutableStateFlow<Pair<String, String>?>(null)
    val notificationEvent: StateFlow<Pair<String, String>?> = _notificationEvent

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getTasks()
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(Task(title = title))
            _notificationEvent.value = "Nueva tarea" to "Se agregó: $title"
            loadTasks()
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(completed = !task.completed))
            _notificationEvent.value = "Tarea actualizada" to "Se actualizó: ${task.title}"
            loadTasks()
        }
    }

    fun deleteTask(taskId: String, title: String) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
            _notificationEvent.value = "Tarea eliminada" to "Se eliminó: $title"
            loadTasks()
        }
    }

    fun editTask(task: Task, newTitle: String) {
        viewModelScope.launch {
            repository.updateTask(task.copy(title = newTitle))
            _notificationEvent.value = "Tarea editada" to "Nuevo título: $newTitle"
            loadTasks()
        }
    }

    fun clearNotificationEvent() {
        _notificationEvent.value = null
    }
}