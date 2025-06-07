package com.example.commercialcatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.commercialcatalog.core.navigation.NavigationWrapper
import com.example.commercialcatalog.ui.theme.CommercialCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val darkTheme = isSystemInDarkTheme()
            CommercialCatalogTheme(darkTheme = darkTheme) {
                NavigationWrapper()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CommercialCatalogTheme(darkTheme = true) {
        NavigationWrapper()
    }
}