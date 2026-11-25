package com.example.examen_parcial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val resultado = findViewById<TextView>(R.id.txtResultados)
        val tvTotalFinal = findViewById<TextView>(R.id.tvTotalFinal)
        val volver = findViewById<Button>(R.id.btnVolver)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val correo = intent.getStringExtra("correo") ?: ""
        val detalleProductos = intent.getStringExtra("detalleProductos") ?: ""
        val subtotal = intent.getDoubleExtra("subtotal", 0.0)
        val adicionales = intent.getStringExtra("adicionales") ?: "Ninguno"
        val montoAdicional = intent.getDoubleExtra("montoAdicional", 0.0)
        val subtotal2 = intent.getDoubleExtra("subtotal2", 0.0)
        val descuento = intent.getIntExtra("descuento", 0)
        val montoDescuento = intent.getDoubleExtra("montoDescuento", 0.0)
        val total = intent.getDoubleExtra("total", 0.0)
        val calificacion = intent.getFloatExtra("calificacion", 0f)

        resultado.text =
            "Cliente: $nombre\n" +
                    "Correo: $correo\n\n" +
                    "Productos cotizados:\n$detalleProductos\n\n" +
                    "Subtotal muebles: S/. ${String.format("%.2f", subtotal)}\n\n" +
                    "Servicios adicionales:\n$adicionales\n" +
                    "Monto adicionales: S/. ${String.format("%.2f", montoAdicional)}\n\n" +
                    "Subtotal con adicionales: S/. ${String.format("%.2f", subtotal2)}\n" +
                    "Descuento aplicado: $descuento% (- S/. ${String.format("%.2f", montoDescuento)})\n" +
                    "Calificación: ${calificacion.toInt()} / 5 estrellas"

        tvTotalFinal.text = "S/. ${String.format("%.2f", total)}"

        volver.setOnClickListener {
            finish()
        }
    }
}