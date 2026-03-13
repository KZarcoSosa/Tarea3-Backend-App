package com.example.apptarea3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apptarea3.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

// Actividad para el inicio de sesión del usuario
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de ViewBinding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción al presionar el botón de Iniciar Sesión
        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            // Validación básica de que los campos no estén vacíos
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                loginUser(user, pass)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método que gestiona la petición de Login con el servidor
    private fun loginUser(user: String, pass: String) {
        // Lanzamos una corrutina en el hilo principal para manejar la respuesta
        lifecycleScope.launch {
            try {
                val request = AuthRequest(user, pass)
                val response = ApiClient.service.login(request)

                when (response.code()) {
                    200 -> {
                        // Inicio de sesión exitoso: Navegamos a la pantalla de bienvenida
                        val intent = Intent(this@LoginActivity, WelcomeActivity::class.java).apply {
                            putExtra("USER_NAME", user)
                        }
                        startActivity(intent)
                        finish() // Finaliza esta actividad para que no se regrese al login con el botón de atrás
                    }
                    401 -> {
                        // Error de autenticación (usuario o contraseña incorrectos)
                        Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Otros errores de respuesta del servidor
                        Toast.makeText(this@LoginActivity, "Error del servidor: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Error de red o conexión al servidor local
                Toast.makeText(this@LoginActivity, "Error de red: Verifica que el servidor esté encendido", Toast.LENGTH_LONG).show()
            }
        }
    }
}
