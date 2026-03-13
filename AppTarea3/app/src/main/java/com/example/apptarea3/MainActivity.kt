package com.example.apptarea3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.apptarea3.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

// Actividad principal que sirve como punto de entrada y verifica el estado del servidor
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de ViewBinding para acceder a los componentes de la interfaz
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verificación inicial de conexión con el servidor (API)
        checkApiStatus()

        // Navegación a la pantalla de Registro
        binding.btnGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Navegación a la pantalla de Inicio de Sesión
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    // Método para comprobar si el servidor está en línea utilizando Corrutinas
    private fun checkApiStatus() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.service.getStatus()
                if (response.isSuccessful) {
                    // Actualiza el texto con el mensaje de éxito del servidor
                    binding.tvStatus.text = response.body()?.mensaje ?: "API Conectada"
                } else {
                    binding.tvStatus.text = "Error en el servidor: ${response.code()}"
                }
            } catch (e: Exception) {
                // Manejo de errores de red (ej. servidor apagado o sin internet)
                binding.tvStatus.text = "Error de conexión"
                Toast.makeText(this@MainActivity, "Error de red: No se pudo conectar al servidor", Toast.LENGTH_LONG).show()
            }
        }
    }
}
