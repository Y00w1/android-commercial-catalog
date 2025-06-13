package com.example.commercialcatalog.view.catalog.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.commercialcatalog.model.Product

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}
