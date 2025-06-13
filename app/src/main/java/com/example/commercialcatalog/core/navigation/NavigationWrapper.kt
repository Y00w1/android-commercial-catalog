package com.example.commercialcatalog.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.commercialcatalog.view.catalog.CatalogScreen
import com.example.commercialcatalog.view.login.LoginScreen
import com.example.commercialcatalog.view.todo.TaskScreen
import com.example.commercialcatalog.viewModel.LoginViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen { email -> navController.navigate(ToDo(email = email)) }
        }
        composable<ToDo> { backStackEntry ->
            val todo: ToDo = backStackEntry.toRoute()
            val loginViewModel = androidx.lifecycle.viewmodel.compose.viewModel<LoginViewModel>()
            TaskScreen(
                onNavigateToCatalog = { navController.navigate(Catalog(todo.email)) },
                onLogout = {
                    loginViewModel.logout()
                    navController.popBackStack(Login, inclusive = false)
                }
            )
        }
        composable<Catalog> { backStackEntry ->
            val catalog: Catalog = backStackEntry.toRoute()
            val loginViewModel = androidx.lifecycle.viewmodel.compose.viewModel<LoginViewModel>()
            CatalogScreen(
                email = catalog.email,
                onNavigateToTodo = { navController.navigate(ToDo(catalog.email)) },
                onLogout = {
                    loginViewModel.logout()
                    navController.popBackStack(Login, inclusive = false)
                }
            )
        }
    }
}