package com.example.commercialcatalog.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.commercialcatalog.catalog.CatalogScreen
import com.example.commercialcatalog.login.LoginScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login>{
            LoginScreen{navController.navigate(Catalog)}
        }
        composable<Catalog>{
            CatalogScreen (onLogout = { navController.navigate(Login) })
        }
    }
}