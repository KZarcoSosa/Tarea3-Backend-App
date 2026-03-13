package com.example.apptarea3

// Estructura de datos para enviar peticiones de autenticación (Registro/Login) al servidor
data class AuthRequest(
    val username: String,
    val password: String
)

// Modelo para manejar la respuesta estándar de la API
data class ApiResponse(
    val mensaje: String? = null,
    val error: String? = null
)
