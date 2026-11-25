package com.example.examen_parcial

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_parcial.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Animación moderna de entrada (fade-in + ligero zoom)
        binding.contenedorSplash.alpha = 0f
        binding.contenedorSplash.scaleX = 0.85f
        binding.contenedorSplash.scaleY = 0.85f
        binding.contenedorSplash.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1200)
            .setInterpolator(DecelerateInterpolator())
            .start()

        // 2. Animar la barra de progreso durante los 5 segundos
        val animadorBarra = ObjectAnimator.ofInt(binding.progressSplash, "progress", 0, 100)
        animadorBarra.duration = 5000
        animadorBarra.start()

        // 3. Esperar 5 segundos y redirigir según la sesión de Firebase
        Handler(Looper.getMainLooper()).postDelayed({
            val usuarioActual = FirebaseAuth.getInstance().currentUser
            if (usuarioActual != null) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 5000)
    }
}