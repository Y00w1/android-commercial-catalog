package com.example.commercialcatalog.view.todo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commercialcatalog.viewModel.TaskViewModel
import com.example.commercialcatalog.view.components.HeaderBar
import com.example.commercialcatalog.view.todo.components.TaskItem
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import com.example.commercialcatalog.model.Task
import com.example.commercialcatalog.notifications.NotificationHandler
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TaskScreen(
    onNavigateToCatalog: () -> Unit,
    onLogout: () -> Unit,
    taskViewModel: TaskViewModel = viewModel()
) {
    val tasks by taskViewModel.tasks.collectAsState()
    var newTask by remember { mutableStateOf("") }
    var showEditDialog by remember { mutableStateOf(false) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }
    var editedTitle by remember { mutableStateOf("") }

    val context = LocalContext.current
    val notificationEvent by taskViewModel.notificationEvent.collectAsState()

    LaunchedEffect(notificationEvent) {
        notificationEvent?.let { (title, message) ->
            NotificationHandler.showLocalNotification(context, title, message)
            taskViewModel.clearNotificationEvent()
        }
    }

    Scaffold(
        topBar = {
            HeaderBar(
                currentScreen = "todo",
                onNavigateToCatalog = onNavigateToCatalog,
                onNavigateToTodo = {},
                onLogout = onLogout
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (newTask.isNotBlank()) {
                        taskViewModel.addTask(newTask)
                        newTask = ""
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newTask,
                onValueChange = { newTask = it },
                label = { Text("Nueva tarea") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onToggle = { taskViewModel.toggleTask(it) },
                        onDelete = { taskViewModel.deleteTask(it, task.title) },
                        onEdit = {
                            taskToEdit = it
                            editedTitle = it.title
                            showEditDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showEditDialog && taskToEdit != null) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    taskToEdit?.let {
                        taskViewModel.editTask(it, editedTitle)
                    }
                    showEditDialog = false
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Editar tarea") },
            text = {
                OutlinedTextField(
                    value = editedTitle,
                    onValueChange = { editedTitle = it },
                    label = { Text("Título de la tarea") }
                )
            }
        )
    }
}