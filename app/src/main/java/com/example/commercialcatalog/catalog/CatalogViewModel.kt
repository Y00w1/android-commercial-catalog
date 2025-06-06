package com.example.commercialcatalog.catalog

import androidx.lifecycle.ViewModel
import com.example.commercialcatalog.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CatalogViewModel : ViewModel() {

    private val _products = MutableStateFlow(
        listOf(
            Product(
                id = "1",
                name = "Zapatos deportivos",
                price = 59.99,
                imageUrl = "https://www.sportline.com.co/media/catalog/product/f/b/fb2208-003_phsrh000-1000.png?optimize=medium&bg-color=255,255,255&fit=bounds&height=&width=&canvas=:&format=jpeg"
            ),
            Product(
                id = "2",
                name = "Camiseta básica",
                price = 19.99,
                imageUrl = "https://404street.com/cdn/shop/files/soadcartoonover.png?v=1743464972&width=533"
            ),
            Product(
                id = "3",
                name = "Gorra negra",
                price = 12.99,
                imageUrl = "https://vansco.vteximg.com.br/arquivos/ids/340755-1000-1000/VN000H2VBLK-1.jpg?v=638573505266570000"
            )
        )
    )
    val products: StateFlow<List<Product>> = _products
}
