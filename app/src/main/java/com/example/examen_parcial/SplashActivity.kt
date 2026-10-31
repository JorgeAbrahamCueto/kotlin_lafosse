package com.example.examen_parcial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.concurrent.thread
import android.content.Intent
import android.widget.ProgressBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        val barra = findViewById<ProgressBar>(R.id.progressBar)

        thread {
            for (i in 1..100) {
                Thread.sleep(50)
                runOnUiThread {
                    barra.progress = i
                }
            }
            runOnUiThread {
                val ventana = Intent(this, MainActivity::class.java)
                startActivity(ventana)
                finish()
            }
        }
    }
}