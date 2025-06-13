package com.example.commercialcatalog.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Catalog(val email: String)

@Serializable
data class ToDo(val email: String)