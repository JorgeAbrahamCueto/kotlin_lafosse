package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vista = inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )

        val catalogo = vista.findViewById<Button>(R.id.btnCatalogo)

        catalogo.setOnClickListener {

            val ventana = Intent(
                requireContext(),
                CatalogoActivity::class.java
            )

            startActivity(ventana)
        }

        return vista
    }
}