package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_parcial.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnRegistrar.setOnClickListener {
            val nombre = binding.etNombreRegistro.text.toString().trim()
            val correo = binding.etCorreoRegistro.text.toString().trim()
            val password = binding.etPasswordRegistro.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || password.length < 6) {
                Toast.makeText(this, "Completa los campos (contraseña mín. 6 caracteres)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(correo, password)
                .addOnSuccessListener { result ->
                    val uid = result.user?.uid ?: return@addOnSuccessListener
                    val datosUsuario = hashMapOf(
                        "uid" to uid,
                        "nombre" to nombre,
                        "correo" to correo
                    )

                    db.collection("usuarios").document(uid).set(datosUsuario)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error al guardar perfil: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al registrar: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }

        binding.tvVolverLogin.setOnClickListener {
            finish()
        }
    }
}