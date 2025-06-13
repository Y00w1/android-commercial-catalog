package com.example.commercialcatalog.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.commercialcatalog.view.catalog.CatalogScreen
import com.example.commercialcatalog.view.login.LoginScreen
import com.example.commercialcatalog.viewModel.LoginViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login>{
            LoginScreen{email -> navController.navigate(Catalog(email = email))}
        }
        composable<Catalog>{ backStackEntry ->
            val catalog: Catalog = backStackEntry.toRoute()
            val loginViewModel = androidx.lifecycle.viewmodel.compose.viewModel<LoginViewModel>()
            CatalogScreen(email = catalog.email) {
                loginViewModel.logout()
                navController.popBackStack()
            }
        }
    }
}