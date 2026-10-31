package com.example.examen_parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vista = inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )

        val usuario = vista.findViewById<EditText>(R.id.txtUsuario)
        val password = vista.findViewById<EditText>(R.id.txtPassword)
        val ingresar = vista.findViewById<Button>(R.id.btnIngresar)

        ingresar.setOnClickListener {

            if (
                usuario.text.toString() == "admin" &&
                password.text.toString() == "123"
            ) {

                Toast.makeText(
                    requireContext(),
                    "Bienvenido Administrador",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Toast.makeText(
                    requireContext(),
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        return vista
    }
}