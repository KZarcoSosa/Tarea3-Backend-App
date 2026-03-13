package com.example.apptarea3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptarea3.databinding.ActivityWelcomeBinding

// Pantalla de bienvenida que se muestra después de un inicio de sesión exitoso
class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de ViewBinding
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera el nombre de usuario pasado desde LoginActivity a través del Intent
        val userName = intent.getStringExtra("USER_NAME") ?: "Usuario"
        
        // Muestra el mensaje de bienvenida personalizado
        binding.tvWelcome.text = "¡Bienvenido/a, $userName!"
    }
}
