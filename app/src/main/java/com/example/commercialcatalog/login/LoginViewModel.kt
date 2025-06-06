package com.example.commercialcatalog.login

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.commercialcatalog.firebase.AuthService

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newEmail: String) {
        if (newEmail.length <= 25) {
            uiState = uiState.copy(email = newEmail)
        }
    }

    fun onPasswordChange(newPassword: String) {
        if (newPassword.length <= 15) {
            uiState = uiState.copy(password = newPassword)
        }
    }

    fun togglePasswordVisibility() {
        uiState = uiState.copy(isPasswordVisible = !uiState.isPasswordVisible)
    }

    fun login(context: Context, onSuccess: () -> Unit) {
        if (uiState.email.isBlank() || uiState.password.isBlank()) {
            uiState = uiState.copy(errorMessage = "Por favor completa todos los campos")
            return
        }

        uiState = uiState.copy(isLoading = true, errorMessage = null)

        AuthService.login(uiState.email, uiState.password,
            onSuccess = {
                uiState = uiState.copy(loginSuccess = true)
                onSuccess()
            },
            onError = { error ->
                uiState = uiState.copy(errorMessage = error)
            },
            onComplete = {
                uiState = uiState.copy(isLoading = false)
            }
        )
    }
}

