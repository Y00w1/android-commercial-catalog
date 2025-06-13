package com.example.commercialcatalog.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object AuthService {

    private val db = Firebase.firestore

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit,
        onComplete: () -> Unit
    ) {
        db.collection("user")
            .document(email)
            .get()
            .addOnSuccessListener { document ->
                val storedPassword = document.getString("password")
                if (storedPassword == password) {
                    onSuccess()
                } else {
                    onError("Credenciales incorrectas")
                }
            }
            .addOnFailureListener {
                onError("Error al verificar el usuario: ${it.message}")
            }
            .addOnCompleteListener {
                onComplete()
            }
    }
}
