package com.example.commercialcatalog.catalog.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
    email: String,
    onLogout: () -> Unit
) {
    TopAppBar(
        title = { Text("Catálogo\n$email") },
        actions = {
            TextButton(onClick = onLogout) {
                Text("Cerrar sesión")
            }
        }
    )
}