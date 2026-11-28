package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MueblesFragment : Fragment(R.layout.fragment_muebles) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycleMuebles)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val listaMuebles = listOf(
            Mueble("Silla Ejecutiva", "Respaldo de malla transpirable y soporte lumbar.", "S/. 450.00", R.drawable.silla_ejecutiva),
            Mueble("Escritorio Gerencial en L", "Melamina de 18mm con cajonera con llave.", "S/. 1,200.00", R.drawable.escritorio_gerencial),
            Mueble("Mesa de Reuniones", "Capacidad para 8 personas con estructura metálica.", "S/. 2,100.00", R.drawable.mesa_reuniones),
            Mueble("Silla Grencial", "Silla elegante para el gerente de hoy.", "S/. 280.00", R.drawable.silla_gerencial),
            Mueble("Butaca de Auditorio", "Asiento abatible tapizado en tela de alto tránsito.", "S/. 650.00", R.drawable.butaca_auditorio),
            Mueble("Modulo de Recepcion", "Comodo modulo para la recepcion","S/. 600.00", R.drawable.modulo_recepcion),
            Mueble("Archivador Metalico", "Archivador de metalico", "S/. 1,500.00", R.drawable.archivador_metalico)
        )

        recycler.adapter = MuebleAdapter(listaMuebles) { mueble ->
            Toast.makeText(requireContext(), "Cotizar: ${mueble.nombre}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), CatalogoActivity::class.java))
        }
    }
}