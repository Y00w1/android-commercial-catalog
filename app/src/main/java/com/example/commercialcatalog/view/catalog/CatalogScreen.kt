package com.example.commercialcatalog.view.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.commercialcatalog.view.catalog.components.ProductList
import com.example.commercialcatalog.viewModel.CatalogViewModel
import com.example.commercialcatalog.view.components.HeaderBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatalogScreen(
    email: String,
    onNavigateToTodo: () -> Unit,
    onLogout: () -> Unit,
    viewModel: CatalogViewModel = viewModel()
) {
    val products by viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            HeaderBar(
                currentScreen = "catalog",
                onNavigateToCatalog = {},
                onNavigateToTodo = onNavigateToTodo,
                onLogout = onLogout
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ProductList(products)
        }
    }
}