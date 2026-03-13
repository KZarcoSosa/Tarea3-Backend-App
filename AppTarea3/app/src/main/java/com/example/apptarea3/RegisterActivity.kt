package com.example.apptarea3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apptarea3.databinding.ActivityRegisterBinding
import kotlinx.coroutines.launch

// Actividad para el registro de nuevos usuarios en el sistema
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de ViewBinding para acceder a los componentes del layout
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción al presionar el botón de Registrarse
        binding.btnRegister.setOnClickListener {
            val user = binding.etUser.text.toString().trim()
            val pass = binding.etPassword.text.toString().trim()

            // Validación de campos antes de enviar la petición
            if (user.isNotEmpty() && pass.isNotEmpty()) {
                registerUser(user, pass)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método que gestiona la petición de Registro utilizando Retrofit y Corrutinas
    private fun registerUser(user: String, pass: String) {
        lifecycleScope.launch {
            try {
                val request = AuthRequest(user, pass)
                val response = ApiClient.service.register(request)

                when (response.code()) {
                    201 -> {
                        // El código 201 indica que el recurso fue creado exitosamente en el servidor
                        Toast.makeText(this@RegisterActivity, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
                        finish() // Regresa a la pantalla anterior (MainActivity) tras el registro
                    }
                    400 -> {
                        // El servidor detecta un error en la solicitud (ej. usuario ya registrado)
                        Toast.makeText(this@RegisterActivity, "El usuario ya existe en el sistema", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        // Otros códigos de error inesperados
                        Toast.makeText(this@RegisterActivity, "Error inesperado: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                // Manejo de errores de conexión de red
                Toast.makeText(this@RegisterActivity, "Error de red: No se pudo conectar al servidor", Toast.LENGTH_LONG).show()
            }
        }
    }
}
