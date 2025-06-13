package com.example.commercialcatalog.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    currentScreen: String,
    onNavigateToCatalog: () -> Unit,
    onNavigateToTodo: () -> Unit,
    onLogout: () -> Unit
) {
    TopAppBar(
        title = { Text(if (currentScreen == "catalog") "Catálogo" else "ToDo List") },
        actions = {
            IconButton(
                onClick = onNavigateToTodo,
                enabled = currentScreen != "todo"
            ) {
                Icon(Icons.Default.List, contentDescription = "ToDo")
            }
            IconButton(
                onClick = onNavigateToCatalog,
                enabled = currentScreen != "catalog"
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Catálogo")
            }
            IconButton(onClick = onLogout) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
            }
        }
    )
}