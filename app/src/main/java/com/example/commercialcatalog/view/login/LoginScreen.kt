package com.example.commercialcatalog.view.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.commercialcatalog.view.login.components.EmailField
import com.example.commercialcatalog.view.login.components.PasswordField
import com.example.commercialcatalog.viewModel.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    navigateToCatalog: (String) -> Unit
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current

    if (uiState.loginSuccess) {
        navigateToCatalog(uiState.email)
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            EmailField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                isPasswordVisible = uiState.isPasswordVisible,
                onVisibilityToggle = { viewModel.togglePasswordVisibility() },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = {
                    viewModel.login(context) {
                        Toast.makeText(context, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Sign in")
            }

            uiState.errorMessage?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
