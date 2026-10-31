package com.example.examen_parcial

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.examen_parcial.databinding.ActivityMainBinding
import android.content.Intent
import android.widget.Button
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val catalogo = findViewById<Button>(R.id.btnCatalogo)
        val contacto = findViewById<Button>(R.id.btnContacto)

        catalogo.setOnClickListener {
            val ventana = Intent(this, CatalogoActivity::class.java)
            startActivity(ventana)
        }

        contacto.setOnClickListener {
            val ventana = Intent(this, ContactoActivity::class.java)
            startActivity(ventana)
        }
    }
    }
