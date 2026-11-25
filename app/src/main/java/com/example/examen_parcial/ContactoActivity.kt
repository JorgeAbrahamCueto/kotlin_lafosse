package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ContactoActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacto)

        db = FirebaseFirestore.getInstance()

        val nombres = findViewById<EditText>(R.id.txtName)
        val correo = findViewById<EditText>(R.id.txtEmail)
        val telefono = findViewById<EditText>(R.id.txtPhone)
        val rgTipo = findViewById<RadioGroup>(R.id.rgTipoCliente)
        val rbHogar = findViewById<RadioButton>(R.id.rbHogar)
        val mensaje = findViewById<EditText>(R.id.txtMensaje)
        val enviar = findViewById<Button>(R.id.btnEnviar)
        val limpiar = findViewById<Button>(R.id.btnClean)
        val volver = findViewById<Button>(R.id.btnExit)

        // Autocompletar el correo del usuario logueado si existe
        val usuarioActual = FirebaseAuth.getInstance().currentUser
        if (usuarioActual?.email != null) {
            correo.setText(usuarioActual.email)
        }

        enviar.setOnClickListener {
            val strNombre = nombres.text.toString().trim()
            val strCorreo = correo.text.toString().trim()
            val strTelefono = telefono.text.toString().trim()
            val strMensaje = mensaje.text.toString().trim()
            val tipoCliente = if (rbHogar.isChecked) "Hogar" else "Empresa"

            if (strNombre.isEmpty() || strCorreo.isEmpty() || strTelefono.isEmpty() || strMensaje.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contactoMap = hashMapOf(
                "uidUsuario" to (usuarioActual?.uid ?: "anonimo"),
                "nombre" to strNombre,
                "correo" to strCorreo,
                "telefono" to strTelefono,
                "tipoCliente" to tipoCliente,
                "mensaje" to strMensaje,
                "fecha" to Timestamp.now()
            )

            db.collection("contactos")
                .add(contactoMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "¡Gracias $strNombre! Tu mensaje fue enviado.", Toast.LENGTH_LONG).show()
                    nombres.setText("")
                    telefono.setText("")
                    mensaje.setText("")
                    rbHogar.isChecked = true
                    nombres.requestFocus()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al enviar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        limpiar.setOnClickListener {
            nombres.setText("")
            correo.setText("")
            telefono.setText("")
            mensaje.setText("")
            rgTipo.check(R.id.rbHogar)
            nombres.requestFocus()
        }

        volver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}