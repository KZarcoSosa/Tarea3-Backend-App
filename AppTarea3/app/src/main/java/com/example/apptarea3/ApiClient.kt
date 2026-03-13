package com.example.apptarea3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Cliente Singleton para realizar peticiones HTTP con Retrofit
object ApiClient {
    // URL base de la API. (10.0.2.2 apunta al localhost desde el emulador de Android)
    private const val BASE_URL = "http://10.0.2.2:5000/"

    // Instancia del servicio utilizando 'lazy' para que se cree solo cuando se necesite por primera vez
    val service: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Agregamos el conversor de GSON para manejar automáticamente los datos en formato JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
