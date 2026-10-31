package com.example.examen_parcial

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)
        val nombres = findViewById<EditText>(R.id.txtName)
        val correo = findViewById<EditText>(R.id.txtEmail)
        val telefono = findViewById<EditText>(R.id.txtPhone)
        val mensaje = findViewById<EditText>(R.id.txtMensaje)
        val enviar = findViewById<Button>(R.id.btnEnviar)
        val limpiar = findViewById<Button>(R.id.btnClean)
        val volver = findViewById<Button>(R.id.btnExit)

        enviar.setOnClickListener {
            if (nombres.text.toString().isEmpty() || correo.text.toString().isEmpty() || telefono.text.toString().isEmpty() || mensaje.text.toString().isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()
            }
        }

        limpiar.setOnClickListener {
            nombres.setText("")
            correo.setText("")
            telefono.setText("")
            mensaje.setText("")
            nombres.requestFocus()
        }

        volver.setOnClickListener {
            finish()
            }
        }
    }
