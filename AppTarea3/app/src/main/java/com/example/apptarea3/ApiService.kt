package com.example.apptarea3

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// Definición de los endpoints de la API utilizando Retrofit
interface ApiService {
    // Comprueba el estado del servidor
    @GET("/")
    suspend fun getStatus(): Response<ApiResponse>

    // Realiza el registro de un nuevo usuario
    @POST("/register")
    suspend fun register(@Body request: AuthRequest): Response<ApiResponse>

    // Inicia sesión con las credenciales de un usuario
    @POST("/login")
    suspend fun login(@Body request: AuthRequest): Response<ApiResponse>
}
